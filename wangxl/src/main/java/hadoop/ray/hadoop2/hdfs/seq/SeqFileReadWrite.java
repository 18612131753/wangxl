package hadoop.ray.hadoop2.hdfs.seq;

import java.io.IOException;
import java.util.Date;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.SequenceFile.CompressionType;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.util.ReflectionUtils;
import org.junit.Before;
import org.junit.Test;

public class SeqFileReadWrite {

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
		// conf.set( FileSystem.FS_DEFAULT_NAME_KEY , "hdfs://master1:8020");
		conf.set( FileSystem.FS_DEFAULT_NAME_KEY , "hdfs://masterall:9000");
		// 序列化文件名
		path = new Path("/sequence/file.seq");
		path_record = new Path("/sequence/file_record.seq");
		path_block = new Path("/sequence/file_block.seq");
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void WriteFile( ) throws IOException {
		FileSystem fs= FileSystem.get( conf );
		SequenceFile.Writer write = null;
		SequenceFile.Writer write_redord = null;
		SequenceFile.Writer write_block = null;
		Text key =new Text();
		Text value =new Text(); //IntWritable v=new IntWritable();

		try{
			write        = SequenceFile.createWriter( fs, conf , path,  key.getClass(), value.getClass() , CompressionType.NONE );
			write_redord = SequenceFile.createWriter( fs, conf , path_record , key.getClass() , value.getClass() , CompressionType.RECORD );
			write_block  = SequenceFile.createWriter( fs, conf , path_block , key.getClass() , value.getClass() , CompressionType.BLOCK );
			
			for(int i=0;i<1000;i++){
				key.set("key"+i);
				// value越长压缩才有效果
				value.set("valuevaluevaluevalue"+i);
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

	@SuppressWarnings("deprecation")
	@Test
	public void ReadFile( ) throws IOException {
		
		FileSystem fs= FileSystem.get( conf );
		SequenceFile.Reader read =null;
		try{
			//path  path_record   path_block
			read = new SequenceFile.Reader(fs,path_record,conf);
			Text k=(Text)ReflectionUtils.newInstance(read.getKeyClass(),conf);
			Text v=(Text)ReflectionUtils.newInstance(read.getValueClass(),conf);
			Date start_date = new Date();
			while(read.next(k,v)){
				System.out.println(k+"\t"+v) ;
			}
			Date over_date = new Date();
			System.out.println( over_date.getTime() - start_date.getTime()) ;
		}catch(Exception e){
			e.printStackTrace();
		} finally {
			IOUtils.closeStream(read);
		}
	}
}
