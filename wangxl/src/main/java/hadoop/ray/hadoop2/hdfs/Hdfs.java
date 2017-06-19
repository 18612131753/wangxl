package hadoop.ray.hadoop2.hdfs;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Before;
import org.junit.Test;
public class Hdfs {
	
	private Configuration conf ;
	
	@Before
	public void init() {
		System.out.println( "初始化配置文件");
		conf=new Configuration();
		
		//客户端在写失败的时候，是否使用更换策略，默认是true没有问题。
		conf.set("dfs.client.block.write.replace-datanode-on-failure.enable","true" ); 
		//default在3个或以上备份的时候，是会尝试更换结点尝试写入datanode。而在两个备份的时候，不更换datanode，直接开始写。对于3个datanode的集群，只要一个节点没响应写入就会出问题，所以可以关掉。
		//NEVER: never add a new datanode
		//DEFAULT:
		conf.set("dfs.client.block.write.replace-datanode-on-failure.policy","NEVER" ); 
		//conf.addResource("core-site.xml");
		// 配置hadoop的namenode节点，看hadoop的/conf/core-site.xml
		// 一定要是active节点
		//conf.set( FileSystem.FS_DEFAULT_NAME_KEY , "hdfs://master2:8020");
		conf.set( FileSystem.FS_DEFAULT_NAME_KEY , "hdfs://masterall:9000");
	}
	
	/**
	 * 创建文件夹
	 * hadoop fs -mkdir /dir/fsmk
	 */
	@Test
	public void createDir( ) throws IOException{
		FileSystem hdfs= FileSystem.get( conf );
        Path dfs=new Path("/dir/javamk/hello1");
        boolean bool = hdfs.mkdirs(dfs);
        hdfs.close();
        System.out.println( "创建"
        		+ "夹:"+(bool?"成功":"失败"));
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
        Path dfs=new Path("/hello/createfile/createFile1.txt");
        FSDataOutputStream outputStream=hdfs.create(dfs);
        outputStream.write( buff );
        outputStream.close();
        hdfs.close();
        System.out.println( "创建文件成功" );
	}
	
	/**
	 * 追加文件内容，跟写文件类似，一个调用创建API，一个创建文件流，直接写
	 * conf.set("dfs.client.block.write.replace-datanode-on-failure.enable","true" ); 
	 * conf.set("dfs.client.block.write.replace-datanode-on-failure.policy","NEVER" );
	 */
	@Test
	public void appendFileContext( ) throws IOException{
		System.out.println( "追加内容" );
        FileSystem hdfs=FileSystem.get( conf );
        String path = "/hello/createfile/createFile1.txt";
        //两句一样，FSDataOutputStream最终也继承OutputStream
        //FSDataOutputStream out = hdfs.append(new Path(path));
        OutputStream out = hdfs.append(new Path(path));
        out.write( ("\n append context"+((new Date()).toString())).getBytes() );
        out.close();
        hdfs.close();
        System.out.println( "结束" );
        //要追加的文件流，inpath为文件  
        //InputStream in = new   
//              BufferedInputStream(new FileInputStream(inpath));  
//        OutputStream out = fs.append(new Path(hdfs_path));  
//        IOUtils.copyBytes(in, out, 4096, true);  
	}
	/**
	 * 复制文件
	 * hadoop fs -copyFromLocal a.txt /hello/c.txt  等价于-put
	 * hadoop fs -moveFromLocal <localsrc> … <dst>  等同于-put，只不过源文件在拷贝后被删除。
	 */
	@Test
	public void copyFile( ) throws IOException{
		FileSystem hdfs=FileSystem.get(conf);
		//本地文件
        Path src =new Path("D:\\logs");
        //HDFS为止
        Path dst =new Path("/hello/logs");
        hdfs.copyFromLocalFile(src, dst);
        //hdfs.moveFromLocalFile(src, dst);
        hdfs.close();
	}
	
	/**
	 * 重命名文件
	 * hadoop fs -mv /a.txt /a1.txt 
	 */
	@Test
	public void renameFile( ) throws IOException{
		FileSystem hdfs=FileSystem.get(conf);
        Path frpaht=new Path("/hello/createfile/createFile.txt");     //旧的文件名
        Path topath=new Path("/hello/createfile/createFile-rename.txt");    //新的文件名
        boolean isRename=hdfs.rename(frpaht, topath);
        System.out.println("文件重命名结果为："+(isRename?"成功":"失败"));
	}

	/**
	 * 读取文件内容
	 * hadoop dfs -cat /a.txt
	 * */
	@Test
	public void readFromHdfs() throws FileNotFoundException,IOException {
		  String dst = "/hello/createfile/createFile.txt";  
		  FileSystem fs = FileSystem.get(conf);
		  FSDataInputStream hdfsInStream = fs.open(new Path(dst));
		  byte[] ioBuffer = new byte[64];
		  int readLen = 0 ;
		  StringBuffer value = new StringBuffer();
		  while( (readLen = hdfsInStream.read(ioBuffer) ) != -1 ){
			  value.append(new String( ioBuffer , 0, readLen ));  
		  }
		 
		  hdfsInStream.close();
		  fs.close();
		  System.out.println( value.toString() );
	}
	
	/**
	 * 读取HDFS路径下所有文件
     *  hadoop fs -ls /hello
	 */
	@Test
	public  void findFile( ) throws IOException{
		FileSystem hdfs=FileSystem.get(conf);
		Path listf =new Path("/hello");
		FileStatus stats[]=hdfs.listStatus(listf);
		for(int i = 0; i < stats.length; ++i) {
			System.out.print( (stats[i].isFile()?"文件     ":"文件夹")+"  ");
			System.out.println( stats[i].getPath().toString() );
		}
		hdfs.close();
	}
	
	/**
	 * 删除文件或者文件夹
	 * hadoop fs -rm /hello/createfile    删除文件可以，文件夹删除不了
	 * hadoop fs –rmr /hello/createfile   删除文件夹
	 */
	@Test
	public void deleteFile( ) throws IOException{
		boolean isDeleted = false ;
		FileSystem hdfs=FileSystem.get(conf);
		//删除路径 
		//delete第二个参数 ：递归删除   true=删除文件夹下全部东西   false =文件删除，文件夹不做处理
		Path delPath = new Path("/hello/createfile");
		isDeleted = hdfs.delete(delPath, true);
		System.out.println("删除文件夹："+(isDeleted?"成功":"失败"));
		
		Path delFile = new Path("/hello/a.txt");
		isDeleted = hdfs.delete(delFile, false);
		System.out.println("删除文件："+(isDeleted?"成功":"失败"));
		hdfs.close();
	}
	
	/**
	 * 下载文件
	 * hadoop dfs -get /hello/a.txt fa.txt
	 * hadoop fs -copyToLocal <src> <localdst>：等价于-get。  hadoop fs -copyToLocal /a1.txt ./a1.txt
	 * */
	@Test
	public void downFile() throws IOException {
		Path dstPath = new Path("/hello/hello2.txt") ;  
		FileSystem dhfs = dstPath.getFileSystem(conf) ;
		dhfs.copyToLocalFile(false, dstPath ,new Path("D:\\logs")) ;
		dhfs.close();

	}
	
}
