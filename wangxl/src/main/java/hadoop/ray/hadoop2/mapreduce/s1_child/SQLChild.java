package hadoop.ray.hadoop2.mapreduce.s1_child;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.yarn.conf.YarnConfiguration;

public class SQLChild {

	public static void main(String[] args) throws Exception {
		
		String hdfs = "hdfs://masterall:9000" ;
		// hadoop配置类
		Configuration conf = new YarnConfiguration();
		// 配置hadoop的namenode节点，看hadoop的/conf/core-site.xml
		conf.set( FileSystem.FS_DEFAULT_NAME_KEY ,  hdfs );
		
		//hadoop2要添加这两句
		conf.set("mapreduce.framework.name", "yarn");  
		// conf.set("yarn.resourcemanager.address", "master1:8032");
		conf.set("yarn.resourcemanager.address", "masterall:8032");
		conf.set("mapred.jar","D:\\boxlog\\sqlchild.jar"); 
		 
		// 结果集输出路径，jar文件会存放在/home/soft01(hadoop用户)/mapred/staging/本计算机名 下
		Path in_path = new Path( hdfs+"/mr_child");
		Path out_path= new Path( hdfs+"/mr_child/output");
		// 删除已经存在的输出目录
		out_path.getFileSystem(conf).delete(out_path,true);
		
		Job job = Job.getInstance(conf , "child") ;
		// job中的map和reduce的推测执行
		//job.setMapSpeculativeExecution(true);
		//job.setReduceSpeculativeExecution(true);

		job.setNumReduceTasks(1);
		job.setJarByClass(SQLChild.class);
		job.setMapperClass(SQLChildMap.class);
		job.setReducerClass(SQLChildReduce.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);

		//设置输入路径和输出路径
		FileInputFormat.addInputPath(job, in_path );
		FileOutputFormat.setOutputPath(job, out_path );

		//坐等结束
		System.exit((job.waitForCompletion(true)) ? 0 : 1);
	}
}