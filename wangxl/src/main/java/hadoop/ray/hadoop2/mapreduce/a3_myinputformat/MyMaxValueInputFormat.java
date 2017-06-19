package hadoop.ray.hadoop2.mapreduce.a3_myinputformat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

public class MyMaxValueInputFormat extends InputFormat<IntWritable,ArrayWritable>{
	
	public static IntWritable[] values = new IntWritable[100]; //100个整数
	
	@Override
	public List<InputSplit> getSplits(JobContext context) throws IOException,
			InterruptedException {
		List<InputSplit> list = new ArrayList<InputSplit>();
		Random rand = new Random();
		for( int i=0;i<100;i++){
			MyMaxValueInputFormat.values[i] = new IntWritable(rand.nextInt(10000)+1) ;
		}
		MyMaxValueInputSplit split1 = new MyMaxValueInputSplit( 0 , 49 );
		MyMaxValueInputSplit split2 = new MyMaxValueInputSplit( 50, 99 );
		list.add(split1);
		list.add(split2);
		return list ;
	}

	@Override
	public RecordReader<IntWritable, ArrayWritable> createRecordReader(
			InputSplit split, TaskAttemptContext context) throws IOException,
			InterruptedException {
		return new MyMaxValueRecordReader();
	}

}
