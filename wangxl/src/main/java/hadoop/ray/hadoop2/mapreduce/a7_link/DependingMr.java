package hadoop.ray.hadoop2.mapreduce.a7_link;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.jobcontrol.ControlledJob;
import org.apache.hadoop.mapreduce.lib.jobcontrol.JobControl;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * 组合式
 * */
public class DependingMr {
	public static class Mapper1 extends Mapper<Object, Text, Text, Text> {
		private Text word = new Text();
		public void map(Object key, Text value, Context context)
				throws IOException, InterruptedException {
			String itr[] = value.toString().split(" ");
			for (int i = 0; i < itr.length; i++) {
				try {
					Integer.parseInt(itr[i]);
				} catch (Exception e) {
					word.set(itr[i]);
					context.write(word, word);
				}
			}
		}
	}

	public static class Reducer1 extends
			Reducer<Text, Text, NullWritable, Text> {
		public void reduce(Text key, Iterable<Text> values, Context context)
				throws IOException, InterruptedException {
			for (Text val : values) {
				if( val.toString() != null && val.getLength()>0){
					context.write(NullWritable.get(), val);
				}
			}
		}
	}

	public static class Mapper2 extends Mapper<Object, Text, Text, Text> {
		private Text word = new Text();

		public void map(Object key, Text value, Context context)
				throws IOException, InterruptedException {
			String itr[] = value.toString().split("");
			for (int i = 0; i < itr.length; i++) {
				try {
					Integer.parseInt(itr[i]);
				} catch (Exception e) {
					word.set(itr[i]);
					context.write(word, word);
				}
			}
		}
	}

	public static class Reducer2 extends
			Reducer<Text, Text, NullWritable, Text> {
		public void reduce(Text key, Iterable<Text> values, Context context)
				throws IOException, InterruptedException {
			for (Text val : values) {
				if( val.toString() != null && val.getLength()>0){
					context.write(NullWritable.get(), val);
				}
			}

		}
	}

	public static class Mapper3 extends Mapper<Object, Text, Text, IntWritable> {
		private final static IntWritable one = new IntWritable(1);
		private Text word = new Text();

		public void map(Object key, Text value, Context context)
				throws IOException, InterruptedException {
			String itr[] = value.toString().split("");
			for (int i = 0; i < itr.length; i++) {
				word.set(itr[i]);
				context.write(word, one);
			}
		}
	}

	public static class Reducer3 extends
			Reducer<Text, IntWritable, Text, IntWritable> {
		private IntWritable result = new IntWritable();

		public void reduce(Text key, Iterable<IntWritable> values,
				Context context) throws IOException, InterruptedException {
			int sum = 0;
			for (IntWritable val : values) {
				sum += val.get();
			}
			result.set(sum);
			context.write(key, result);
		}
	}

	public static void main(String[] args) throws Exception {
		
		String hdfs = "hdfs://masterall:9000" ;
		
		Configuration conf = new Configuration();
		conf.set( FileSystem.FS_DEFAULT_NAME_KEY , hdfs );
		conf.set("mapreduce.framework.name", "yarn");  
		conf.set("yarn.resourcemanager.address", "masterall:8032");
		conf.set("mapred.jar","D:\\boxlog\\dependingMr.jar");

		Path input= new Path( hdfs + "/mr_depend/input" );
		Path out1= new Path( hdfs + "/mr_depend/out1" );
		Path input2= out1 ;
		Path out2= new Path( hdfs + "/mr_depend/out2" );
		Path input3= out2 ;
		Path out3= new Path( hdfs + "/mr_depend/out3" );
		
		out1.getFileSystem(conf).delete(out1,true);
		out2.getFileSystem(conf).delete(out2,true);
		out3.getFileSystem(conf).delete(out3,true);
		
		Job job1 = Job.getInstance(conf , "word remove num1"); // 设置一个用户定义的job名称
		job1.setJarByClass(DependingMr.class);
		job1.setMapperClass(Mapper1.class); // 为job设置Mapper类
		job1.setReducerClass(Reducer1.class); // 为job设置Reducer类
		job1.setOutputKeyClass(Text.class); // 为Map的输出数据设置Key类
		job1.setOutputValueClass(Text.class); // 为Map输出设置value类
		FileInputFormat.addInputPath(job1, input ); // 为job设置输入路径
		FileOutputFormat.setOutputPath(job1, out1 );// 为job设置输出路径

		Job job2 = Job.getInstance(conf , "word remove num12"); // 设置一个用户定义的job名称
		job2.setJarByClass(DependingMr.class);
		job2.setMapperClass(Mapper2.class); // 为job设置Mapper类
		job2.setReducerClass(Reducer2.class); // 为job设置Reducer类
		job2.setOutputKeyClass(Text.class); // 为Map的输出数据设置Key类
		job2.setOutputValueClass(Text.class); // 为Map输出设置value类
		FileInputFormat.addInputPath(job2, input2); // 为job设置输入路径
		FileOutputFormat.setOutputPath(job2, out2);// 为job设置输出路径
		
		Job job3 = Job.getInstance(conf , "word count"); // 设置一个用户定义的job名称
		job3.setJarByClass(DependingMr.class);
		job3.setMapperClass(Mapper3.class); // 为job设置Mapper类
		job3.setReducerClass(Reducer3.class); // 为job设置Reducer类
		job3.setOutputKeyClass(Text.class); // 为Map的输出数据设置Key类
		job3.setOutputValueClass(IntWritable.class); // 为Map输出设置value类
		FileInputFormat.addInputPath(job3, input3); // 为job设置输入路径
		FileOutputFormat.setOutputPath(job3, out3);// 为job设置输出路径

		ControlledJob controlledJob1 = new ControlledJob( job1.getConfiguration());
		controlledJob1.setJob(job1);
		
		ControlledJob controlledJob2 = new ControlledJob( job2.getConfiguration());
		controlledJob2.setJob(job2);
		controlledJob2.addDependingJob(controlledJob1);  //依赖任务
		
		ControlledJob controlledJob3 = new ControlledJob( job3.getConfiguration());
		controlledJob3.setJob(job3);
		
		controlledJob3.addDependingJob(controlledJob1);   //依赖任务
		controlledJob3.addDependingJob(controlledJob2);   //依赖任务
		
		JobControl jc = new JobControl("depend");
		jc.addJob(controlledJob1);
		jc.addJob(controlledJob2);
		jc.addJob(controlledJob3);
		Thread jcThread = new Thread(jc);//如果直接调用run，线程无法结束
		jcThread.start();
		while (true) {
			if (jc.allFinished()) {
				System.out.println(jc.getSuccessfulJobList());
				jc.stop();
				System.exit(0);
			}
			if (jc.getFailedJobList().size() > 0) {
				System.out.println(jc.getFailedJobList());
				jc.stop();
				System.exit(1);
			}
		}
	}
}
