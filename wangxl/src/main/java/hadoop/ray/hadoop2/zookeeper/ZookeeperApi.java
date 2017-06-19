package hadoop.ray.hadoop2.zookeeper;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class ZookeeperApi implements Watcher {

	private static final int SESSION_TIMEOUT = 10000;
	private static final String CONNECTION_STRING = "192.168.192.146:2181,192.168.192.146:2182,192.168.192.146:2183";
	private ZooKeeper zk = null;

	//同步计数器
	private CountDownLatch connectedSemaphore = new CountDownLatch(1);

	/**
	 * 收到来自Server的Watcher通知后的处理。
	 */
	@Override
	public void process(WatchedEvent event) {
		System.out.println("watcher收到的事件："+event.getType().name()+" "+event.getState());
		if (KeeperState.SyncConnected == event.getState()) {
			connectedSemaphore.countDown();
		}
		
		//监听一次结束后，重复监听
//		try {
//			if( event.getPath() != null )
//				this.zk.exists( event.getPath() , this);
//		} catch (KeeperException e) {
//			e.printStackTrace();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
	}

	public static void main(String[] args) throws Exception {

		ZookeeperApi sample = new ZookeeperApi();
		sample.zk = new ZooKeeper(CONNECTION_STRING, SESSION_TIMEOUT, sample);
		sample.connectedSemaphore.await();
		System.out.println("创建zk连接成功\n");
		
		//创建路径
		//参数解释：path 内容  权限  path类型
		try {
			sample.zk.exists("/test", sample);
		} catch (Exception e) {
		//	e.printStackTrace();
			System.out.println( "不存在") ;
		}
		
		//OPEN_ACL_UNSAFE : 不进行ACL权限控制
		//CREATOR_ALL_ACL : 创建者全部权限
		//READ_ACL_UNSAFE : 创建只读文件
		sample.zk.create("/test", 
				"我是节点初始内容".getBytes() , 
				Ids.OPEN_ACL_UNSAFE , 
				CreateMode.PERSISTENT);
		System.out.println("创建/test路径成功\n");
		
		//获取内容
		//参数解释：path 是否watch 统计信息
		String content = new String(sample.zk.getData("/test", sample, null ));
		System.out.println("读取/test数据: " + content+"\n");
		
		//更新内容
		//参数解释：path 内容   版本
		sample.zk.setData("/test", "新内容".getBytes(), -1 );
		content = new String(sample.zk.getData("/test", sample, null ));
		System.out.println("更新/test数据：" + content+"\n");
		
		//路径是否存在
		//参数解释：path 是否watch
		Stat stat = sample.zk.exists("/test", sample);
		System.out.println("/test状态: " + stat.toString()+"\n");
		
		sample.zk.create("/test/child", 
				"我是节点初始内容".getBytes() , 
				Ids.OPEN_ACL_UNSAFE , 
				CreateMode.PERSISTENT);
		sample.zk.exists("/test", sample);
		
		//获取孩子节点
		List<String> list = sample.zk.getChildren("/test", sample);
		System.out.println("/test孩子节点: " + list.toString()+"\n");
		
		
		
		Thread.sleep( 30000 );
		//删除节点
		sample.zk.delete("/test", -1);
		System.out.println("删除/test节点\n" );
		
		//关闭zk连接
		if (sample.zk!=null){
			sample.zk.close();
			System.out.println("关闭连接点\n" );
		}
			
	}
}