package hadoop.ray.hadoop2.HA;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Before;
import org.junit.Test;

/**
 * namenode的HA设置
 * */
public class HAReadWrite {

	private Configuration conf ;
	
	@Before
	public void init() {
		System.out.println( "初始化配置文件");
		conf=new Configuration();
//		conf.addResource("core-site.xml");
		conf.set( "ha.zookeeper.quorum" , "slave1:2181,slave2:2181,slave3:2181");
		conf.set( FileSystem.FS_DEFAULT_NAME_KEY , "hdfs://cluster");
		conf.set("dfs.nameservices", "cluster");
		conf.set("dfs.ha.namenodes.cluster", "master1,master2");
		conf.set("dfs.namenode.rpc-address.cluster.master1", "master1:8020");
		conf.set("dfs.namenode.rpc-address.cluster.master2", "master2:8020");
		conf.set("dfs.client.failover.proxy.provider.cluster","org.apache.hadoop.hdfs.server.namenode.ha.ConfiguredFailoverProxyProvider");
	}
	
	/**
	 * 创建文件
	 * hadoop fs -copyFromLocal a.txt /hello/c.txt
	 * hadoop fs -moveFromLocal b.txt /hello/bb.txt
	 */
	@Test
	public void createFile( ) throws IOException{
        FileSystem hdfs=FileSystem.get( conf );
        byte[] buff="hello world!\ncreateFile".getBytes();
        Path dfs=new Path("/hello/createfile/createFile.txt");
        FSDataOutputStream outputStream=hdfs.create(dfs);
        outputStream.write( buff );
        hdfs.close();
        System.out.println( "创建文件成功" );
	}
}
