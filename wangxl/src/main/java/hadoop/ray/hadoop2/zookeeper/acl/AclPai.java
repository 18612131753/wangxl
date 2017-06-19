package hadoop.ray.hadoop2.zookeeper.acl;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

public class AclPai implements Watcher{

	private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
	private static final String CONNECTION_STRING = "slave1:2181";
	
	@Override
	public void process(WatchedEvent event) {
		System.out.println("收到事件通知：" + event.getState() + "  " + connectedSemaphore.getCount());
		if (KeeperState.SyncConnected == event.getState()) {
			connectedSemaphore.countDown();
		}
	}
	
	public static void main(String[] args) throws NoSuchAlgorithmException, IOException, KeeperException, InterruptedException {
		List<ACL> acls = new ArrayList<ACL>();

		Id id1 = new Id("world", "anyone");
		ACL acl1 = new ACL(ZooDefs.Perms.ALL, id1);
		
		//Id id2 = new Id("auth", "");
		//ACL acl2 = new ACL(ZooDefs.Perms.ALL, id2);
		//acls.add(acl2);  //zookeeper3.1之前能用，咱们的是3.4
		
		Id id3 = new Id("digest", DigestAuthenticationProvider.generateDigest("guest:guest123"));
		ACL acl3 = new ACL(ZooDefs.Perms.READ, id3);
		
		Id id4 = new Id("ip", "127.0.0.1");
		ACL acl4 = new ACL(ZooDefs.Perms.READ, id4);
		
		acls.add(acl1);
		acls.add(acl3);
		acls.add(acl4);
		
		ZooKeeper zk = new ZooKeeper(CONNECTION_STRING , 10000, new AclPai());
		connectedSemaphore.await();
		//1.用自定义权限创建一个文件夹
		//zk.create("/test", "test".getBytes(), acls, CreateMode.PERSISTENT);
		zk.addAuthInfo("digest", "soft01:tarena".getBytes());
		//zk.setACL("/zk/s20000000004", acls, -1);
		System.out.println(zk.getData("/zk", true, null));
		//2.通过用户查询
		//zk.addAuthInfo("digest", "root:root123".getBytes());
		
		//3.通过用户查询，因为密码写成错误的，所以得不到文件
		//zk.addAuthInfo("digest", "root:root124".getBytes());
		
		//byte[] value = zk.getData("/test", false , null);
		//System.out.println(new String(value));
	}
	
}
