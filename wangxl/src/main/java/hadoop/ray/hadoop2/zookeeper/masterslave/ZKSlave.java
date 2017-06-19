package hadoop.ray.hadoop2.zookeeper.masterslave;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;

public class ZKSlave implements Watcher {

	public ZooKeeper zk ;
	
	public static String[] slaves = new String[]{ "child1","child2" };
	
	public static CountDownLatch countDownLatch = new CountDownLatch(1);
	
	public static void main( String[] args ) throws KeeperException, InterruptedException, IOException{
		//连接的超时时间, 毫秒 
		int SESSION_TIMEOUT = 10000;
		
		ZKSlave slaveWatch = new ZKSlave();
		slaveWatch.setZk( new ZooKeeper( ZKMaster.zkServers , SESSION_TIMEOUT , slaveWatch ) );
		System.out.println( "slaveWatch初始化zk");
		ZKSlave.countDownLatch.await();
		
		for( String s : slaves){
			slaveWatch.getZk().exists(ZKMaster.WPath+"/"+s, slaveWatch);
			System.out.println( "slave开始监听" + ZKMaster.WPath+"/"+s );
		}

		while( true ){
			
		}
	}
	
	//处理
	@Override
	public void process(WatchedEvent event) {
		System.out.println("slave watcher收到的事件："+
				"P:"+event.getPath()+" "+
				"T:"+event.getType().name()+" "+
				"S:"+event.getState());
		
		if( "NodeDeleted".equals(event.getType().name())){
			System.out.println( event.getPath().replaceAll(ZKMaster.WPath+"/", "")+" 节点丢失链接" );
		} else if ( "NodeCreated".equals(event.getType().name())){
			System.out.println( event.getPath().replaceAll(ZKMaster.WPath+"/", "")+" 节点链接" );
		}
		//当master的连接建立后，master继续执行后面的程序
		//event.getState() 两种状态，链接中 或者 已经建立连接
		if (KeeperState.SyncConnected == event.getState() && ZKSlave.countDownLatch.getCount() >0 ) {
			System.out.println( "slave 链接建立完毕：");
			ZKSlave.countDownLatch.countDown();
		}
		
		//监听一次结束后，重复监听
		try {
			//继续监听
			this.getZk().exists( event.getPath() , this);
			//System.out.println( "slave 继续监听");
		} catch (Exception e) {
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