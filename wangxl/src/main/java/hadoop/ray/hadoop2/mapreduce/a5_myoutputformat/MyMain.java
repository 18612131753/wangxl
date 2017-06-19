package hadoop.ray.hadoop2.mapreduce.a5_myoutputformat;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class MyMain {

	public static void main( String[] args ) throws Exception{

		String hdfs = "hdfs://masterall:9000" ;
		
		// hadoop配置类
		Configuration conf = new Configuration();
		// 配置hadoop的namenode节点，看hadoop的/conf/core-site.xml 
		conf.set( FileSystem.FS_DEFAULT_NAME_KEY , hdfs );
		//hadoop2要添加这两句
		conf.set("mapreduce.framework.name", "yarn");  
		conf.set("yarn.resourcemanager.address", "masterall:8032");
		conf.set("mapred.jar","D:\\boxlog\\maxvalueoutput.jar"); 

		Path out= new Path( hdfs+"/maxvalueoutput");
		out.getFileSystem(conf).delete(out,true);

		Job job = Job.getInstance(conf , "maxvalueoutput") ;
		job.setJarByClass(MyMain.class);
	    job.setMapperClass(MyMapper.class);
		job.setReducerClass(MyReducer.class);
		//job.setNumReduceTasks(1);
		job.setMapOutputKeyClass(IntWritable.class);
		job.setMapOutputValueClass(IntWritable.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);

		//重点，自定义InputFormat
		job.setInputFormatClass(MyMaxValueInputFormat.class);
		job.setOutputFormatClass( MyMaxValueOutPutFormat.class);
		
		FileOutputFormat.setOutputPath( job, out );
		System.exit((job.waitForCompletion(true)) ? 0 : 1);
	}
	
	public static class MyMapper extends Mapper<IntWritable, ArrayWritable, IntWritable, IntWritable> {
		public void map(IntWritable key, ArrayWritable value, Context context)
				throws IOException, InterruptedException {
			IntWritable[] int_array = (IntWritable[])value.toArray();
			int maxValue = int_array[0].get() ;
			for( int i=1;i<int_array.length;i++ ){
				if( int_array[i].get()> maxValue ){
					maxValue = int_array[i].get() ;
				}
			}
			//context.write( new IntWritable(maxValue) , new IntWritable(maxValue) );
			context.write( new IntWritable(1) , new IntWritable(maxValue) );
		}
	}
	
	public static class MyReducer extends Reducer<IntWritable, IntWritable, Text, IntWritable> {
		public void reduce(IntWritable key, Iterable<IntWritable> values,
				Context context) throws  IOException, InterruptedException {
			int max = 0 ;
			for( IntWritable val : values ){
				if( val.get() > max ){
					max = val.get() ;
				}
			}
			context.write( new Text("max int is "), new IntWritable(max) );
		}
	}
}
