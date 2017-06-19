package hadoop.ray.hadoop2.hdfs.mapfile;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.MapFile;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.util.ReflectionUtils;
import org.junit.Before;
import org.junit.Test;

public class MapFileReadWrite {

	private Configuration conf ;
	private Path path ;
	
	@Before
	public void init() {
		System.out.println( "初始化配置文件");
		path = new Path("/mapfile/file.map");
		conf=new Configuration();
		//conf.set( FileSystem.FS_DEFAULT_NAME_KEY , "hdfs://master1:8020");
		conf.set( FileSystem.FS_DEFAULT_NAME_KEY , "hdfs://masterall:9000");
	}
	
	@Test
	public void WriteFile( ) throws IOException {
		MapFile.Writer write = null;
		Text key =new Text();
		Text value =new Text(); //IntWritable v=new IntWritable();
		try{
			write = new MapFile.Writer(conf, path, 
					MapFile.Writer.keyClass(key.getClass()) ,
					MapFile.Writer.valueClass(value.getClass()) 
			);
			//必须是从小到大的写，因为是有序的
			for(int i=0;i<10;i++){
				key.set("key"+i);
				value.set("value"+i);
				write.append(key,value);
			}
		}catch(Exception e){
			e.printStackTrace();
		} finally {
			IOUtils.closeStream(write);
		}
		System.out.println( "结束");
	}

	@Test
	public void ReadFile( ) throws IOException {
		
		MapFile.Reader read =null;
		try{
			read=new MapFile.Reader( path , conf  );
			//反射序列化
			Text k = (Text)ReflectionUtils.newInstance( read.getKeyClass(),conf );
			Text v = (Text)ReflectionUtils.newInstance( read.getValueClass(),conf );
			while(read.next(k,v)){
				System.out.println(k+"\t"+v) ;
			}
		}catch(Exception e){
			e.printStackTrace();
		} finally {
			IOUtils.closeStream(read);
		}
	}
	
	@Test
	public void ReadFileByKey( ) throws IOException {
		String keystr = "key0" ;
		MapFile.Reader read =null;
		try{
			read=new MapFile.Reader( path , conf  );
			Text key =new Text( keystr );
			Text value = new Text();
			read.get( key , value );
			System.out.println( value ) ;
		}catch(Exception e){
			e.printStackTrace();
		} finally {
			IOUtils.closeStream(read);
		}
	}
}
