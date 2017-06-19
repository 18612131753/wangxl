package hadoop.ray.hadoop2.hdfs.seq;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.SequenceFile.CompressionType;
import org.apache.hadoop.io.SequenceFile.Writer;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.compress.BZip2Codec;

public class SeqFileReadWriteSnappy {

	public static void main( String[] args ){
		
		Configuration conf=new Configuration();
		// 一定要是active节点
		conf.set( FileSystem.FS_DEFAULT_NAME_KEY , "hdfs://master2:8020");

		SequenceFile.Writer write = null;
		SequenceFile.Writer write_redord = null;
		SequenceFile.Writer write_block = null;
		Text key =new Text();
		Text value =new Text(); //IntWritable v=new IntWritable();
		try{
			//新API，参数全部都是Option类型
			write = SequenceFile.createWriter( conf , 
					SequenceFile.Writer.file(new Path("/sequencenewsnappy/file.seq")) , 
					SequenceFile.Writer.keyClass(key.getClass()) , 
					SequenceFile.Writer.valueClass(value.getClass()) , 
					Writer.compression(CompressionType.NONE) 
			);
			/** 
			 * SequenceFile.Writer.compression(CompressionType.RECORD, new GzipCodec())
			 * 如果制定压缩方式，会报错
			 * hadoop的本地运行库，由于hadoop的一些操作(比如压缩)为了提高性能，不适合使用java的库运行，所以提供一批本地库
			 * core-site.xml
			 * <property>
				  <name>io.compression.codecs</name>
				  <value>org.apache.hadoop.io.compress.GzipCodec,
				    org.apache.hadoop.io.compress.DefaultCodec,   // 默认
				    org.apache.hadoop.io.compress.BZip2Codec,
				    org.apache.hadoop.io.compress.SnappyCodec
				  </value>
				</property>
			**/
			write_redord = SequenceFile.createWriter( conf , 
					SequenceFile.Writer.file(new Path("/sequencenewsnappy/file_record.seq")) , 
					SequenceFile.Writer.keyClass(key.getClass()) , 
					SequenceFile.Writer.valueClass(value.getClass()) , 
					SequenceFile.Writer.compression(CompressionType.RECORD, new BZip2Codec()) 
			);
			write_block = SequenceFile.createWriter( conf , 
					SequenceFile.Writer.file(new Path("/sequencenewsnappy/file_block.seq")) , 
					SequenceFile.Writer.keyClass(key.getClass()) , 
					SequenceFile.Writer.valueClass(value.getClass()) , 
					SequenceFile.Writer.compression(CompressionType.BLOCK, new BZip2Codec())  
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
}
