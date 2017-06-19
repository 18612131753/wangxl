package hadoop.ray.hadoop2.hive;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.SequenceFile.CompressionType;
import org.apache.hadoop.io.SequenceFile.Writer;
import org.apache.hadoop.io.Text;
import org.junit.Before;
import org.junit.Test;

/**
 * 写入hive sequence 文件
 * 分隔符为/t
 * 字段
 * id int ,name string ,pid int,age int
 * 
 * create table person_seq( id string ,name string ,pid string,age string  ) ROW FORMAT DELIMITED FIELDS TERMINATED BY ' ' STORED AS SEQUENCEFILE;
 * load data inpath 'hdfs:////hive_seq' into table person_seq; 
 * */
public class SeqFileWrite_Hive_Person {

	private Configuration conf ;
	private Path path ;
	
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
		path = new Path("/hive_seq/person.seq");
	}
	
	@Test
	public void WriteFile( ) throws IOException {
		//FileSystem fs= FileSystem.get( conf );
		SequenceFile.Writer write = null;
		Text key =new Text();
		Text value =new Text();

		try{
			// write        = SequenceFile.createWriter( fs, conf , path,  key.getClass(), value.getClass() , CompressionType.NONE );
			write = SequenceFile.createWriter( 
						conf ,
                        Writer.file(path), Writer.keyClass(key.getClass()),
                        Writer.valueClass(value.getClass()), 
                        Writer.compression(CompressionType.NONE));
			key.set("a");
			value.set("1 zhang 2 20");
			write.append(key, value);
			
			key.set("b");
			value.set("2 li 3 21");
			write.append(key, value);

			key.set("c");
			value.set("3 zhu 4 22");
			write.append(key, value);
			
			key.set("d");
			value.set("4 liu 5 18");
			write.append(key, value);

		}catch(Exception e){
			e.printStackTrace();
		} finally {
			IOUtils.closeStream(write);
		}
		System.out.println( "结束");
	}

}
