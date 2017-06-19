package hadoop.ray.hadoop2.zookeeper.watch;

import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

/**
 * 1. zk上创建 /test
 * 2. 开始main
 * 3. zk上删除 /test，监听到删除节点的事件
 * 
 * */
public class ZookeeperTest {

	public static void main(String[] args) throws Exception {

		ZkWatch zkWatch = new ZkWatch();
		
		//ZooKeeper zk = new ZooKeeper("slave1:2181,slave2:2181,slave3:2181",10000, zkWatch);
		ZooKeeper zk = new ZooKeeper("192.168.192.146:2181",10000, zkWatch);
		Stat s = zk.exists("/test", zkWatch);
		//如果不存在，s == null
		if( s != null ){
			byte[] b = zk.getData("/test", zkWatch, null) ;
			System.out.println( new String(b, "UTF-8") );
			while(true){
				
			}
		}
		
		zk.close();

	}

}
