package hadoop.ray.hadoop2.zookeeper.masterslave;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;

public class ZKMaster implements Watcher {

	public ZooKeeper zk ;
	
	public static String WPath = "/father" ;
	//C:\Windows\System32\drivers\etc\hosts
	//public static String zkServers = "slave1:2181,slave2:2181,slave3:2181" ;
	public static String zkServers = "192.168.192.146:2181" ;
	public static CountDownLatch countDownLatch = new CountDownLatch(1);
	
	public static void main( String[] args ) throws KeeperException, InterruptedException, IOException{
		//连接的超时时间, 毫秒 
		int SESSION_TIMEOUT = 10000;
		
		ZKMaster master = new ZKMaster();
		master.setZk( new ZooKeeper( ZKMaster.zkServers , SESSION_TIMEOUT , master ) );
		System.out.println( "master初始化zk");
		ZKMaster.countDownLatch.await();
		
		System.out.println( "master开始监听" + ZKMaster.WPath );
		master.getZk().exists( ZKMaster.WPath , master);
		while( true ){
			
		}
	}
	
	@Override
	public void process(WatchedEvent event) {
		System.out.println("master watcher收到的事件："+
				"P:"+event.getPath()+" "+
				"T:"+event.getType().name()+" "+
				"S:"+event.getState());
		//当master的连接建立后，master继续执行后面的程序
		//event.getState() 两种状态，链接中 或者 已经建立连接
		if (KeeperState.SyncConnected == event.getState() && ZKMaster.countDownLatch.getCount() >0 ) {
			System.out.println( "master 链接建立完毕");
			ZKMaster.countDownLatch.countDown();
		}
				
		if( "NodeChildrenChanged".equals(event.getType().name()) ){
			System.out.println( "孩子节点出现变化，请做特殊处理" );
		}
		
		//监听一次结束后，重复监听
		try {
			List<String> list = this.getZk().getChildren( ZKMaster.WPath , this);
			System.out.println( "master 继续监听:"+list.toString());
		} catch (KeeperException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public ZooKeeper getZk() {
		return zk;
	}

	public void setZk(ZooKeeper zk) {
		this.zk = zk;
	}
	
}