package hadoop.ray.hadoop2.mapreduce.a4_mypartitioner;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Counter;
import org.apache.hadoop.mapreduce.Partitioner;

public class MyPartitioner extends Partitioner<IntWritable,IntWritable>{

	public static Counter ct = null ;
	public int i=0;
	@Override
	//返回值：为第几个reduce
	public int getPartition(IntWritable key, IntWritable value, int numPartitions) {
		// 一共有2个key，一个0，一个50
		if( key.get() == 0 ){
			return 0;
		}
		return 1;
	}

}