package hadoop.ray.hadoop2.mapreduce.a2_wordtop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * 例子：取出数据统计排名前5的单词
 * */
public class WordTopMain {
	
	public static void main( String[] args) throws Exception{

		String hdfs = "hdfs://masterall:9000" ;
		
		// hadoop配置类
		Configuration conf = new Configuration();
		// 配置hadoop的namenode节点，看hadoop的/conf/core-site.xml 
		conf.set( FileSystem.FS_DEFAULT_NAME_KEY , hdfs );
		//hadoop2要添加这两句
		conf.set("mapreduce.framework.name", "yarn");  
		conf.set("yarn.resourcemanager.address", "masterall:8032");
		conf.set("mapred.jar","D:\\boxlog\\wordtop.jar"); 
		
		// 结果集输出路径，jar文件会存放在/home/soft01(hadoop用户)/mapred/staging/本计算机名 下
		Path in_path = new Path( hdfs+"/mr_wordtop/input");
		Path out_path= new Path( hdfs+"/mr_wordtop/output");
		
		// 删除已经存在的输出目录
		out_path.getFileSystem(conf).delete(out_path,true);
		// 新建一个mr的任务（job）
		Job job = Job.getInstance(conf , "wordtop") ;
		// 设置执行累
		job.setJarByClass(WordTopMain.class);
		// 配置map函数
		job.setMapperClass(WordTopMapper.class);
		// 配置reduce函数
		job.setReducerClass(WordTopReducer.class);
		//设置reduce个数
		job.setNumReduceTasks(1);
		//推测性执行
		job.setMapSpeculativeExecution(true);
		job.setReduceSpeculativeExecution(true);
		
		// 设置map结果中<k,v> key的类型
		job.setMapOutputKeyClass(Text.class);
		// 设置map结果中<k,v> value的类型
		job.setMapOutputValueClass(IntWritable.class);
		
		// 设置reduce结果中<k,v> key的类型
		job.setOutputKeyClass(Text.class);
		// 设置reduce结果中<k,v> value的类型
		job.setOutputValueClass(IntWritable.class);
		
		// 需要分析的文件路径,MapReduce过程中经常会遇到多个源路径的输入
		FileInputFormat.addInputPath(job, in_path );
		FileOutputFormat.setOutputPath( job, out_path );
		// 等待mr结束
		System.exit((job.waitForCompletion(true)) ? 0 : 1);
	}
	
}