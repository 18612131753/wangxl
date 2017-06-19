package hadoop.ray.hadoop2.hdfs.seq;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.SequenceFile.CompressionType;
import org.apache.hadoop.io.SequenceFile.Writer;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.util.ReflectionUtils;
import org.junit.Before;
import org.junit.Test;

public class SeqFileReadWriteNewApi {

	private Configuration conf ;
	private Path path ;
	private Path path_record ;
	private Path path_block ;
	@Before
	public void init() {
		System.out.println( "初始化配置文件");
		conf=new Configuration();
		//conf.addResource("conf/core-site.xml");
		// 配置hadoop的namenode节点，看hadoop的/conf/core-site.xml
		// 一定要是active节点
		conf.set( FileSystem.FS_DEFAULT_NAME_KEY , "hdfs://master2:8020");
		
		// 序列化文件名
		path = new Path("/sequencenew/file.seq");
		path_record = new Path("/sequencenew/file_record.seq");
		path_block = new Path("/sequencenew/file_block.seq");
	}
	
	@Test
	public void WriteFile( ) throws IOException {
		//FileSystem fs= FileSystem.get( conf );
		SequenceFile.Writer write = null;
		SequenceFile.Writer write_redord = null;
		SequenceFile.Writer write_block = null;
		Text key =new Text();
		Text value =new Text(); //IntWritable v=new IntWritable();
		try{

			//新API，参数全部都是Option类型
			write = SequenceFile.createWriter( conf , 
					SequenceFile.Writer.file(path) , 
					SequenceFile.Writer.keyClass(key.getClass()) , 
					SequenceFile.Writer.valueClass(value.getClass()) , 
					Writer.compression(CompressionType.NONE) 
			);
			write_redord = SequenceFile.createWriter( conf , 
					SequenceFile.Writer.file(path_record) , 
					SequenceFile.Writer.keyClass(key.getClass()) , 
					SequenceFile.Writer.valueClass(value.getClass()) , 
					SequenceFile.Writer.compression(CompressionType.RECORD) 
			);
			write_block = SequenceFile.createWriter( conf , 
					SequenceFile.Writer.file(path_block) , 
					SequenceFile.Writer.keyClass(key.getClass()) , 
					SequenceFile.Writer.valueClass(value.getClass()) , 
					SequenceFile.Writer.compression(CompressionType.BLOCK)  
			);
			
			for(int i=0;i<100;i++){
				key.set("key"+i);
				value.set("value"+i);
				write.append(key,value);
				write_redord.append(key,value);
				write_block.append(key,value);
			}
		}catch(Exception e){
			e.printStackTrace();
		} finally {
			IOUtils.closeStream(write);
			IOUtils.closeStream(write_redord);
			IOUtils.closeStream(write_block);
		}
		System.out.println( "结束");
	}

	@Test
	public void writeFileContinue( ) throws IOException {
		
	}
	@Test
	public void ReadFile( ) throws IOException {
		SequenceFile.Reader read =null;
		try{
			read=new SequenceFile.Reader( conf , SequenceFile.Reader.file(path) );
			//read=new SequenceFile.Reader( conf , SequenceFile.Reader.file(path_record) );
			//read=new SequenceFile.Reader( conf , SequenceFile.Reader.file(path_block) );

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
	
}