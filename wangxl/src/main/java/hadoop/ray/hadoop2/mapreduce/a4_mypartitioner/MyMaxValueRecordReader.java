package hadoop.ray.hadoop2.mapreduce.a4_mypartitioner;

import java.io.IOException;

import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

public class MyMaxValueRecordReader extends RecordReader<IntWritable,ArrayWritable>{

	private MyMaxValueInputSplit myInputSplit = null ;
	
	private IntWritable key = null ;
	private ArrayWritable value = new ArrayWritable(IntWritable.class); ;

	@Override
	public void initialize(InputSplit split, TaskAttemptContext context)
			throws IOException, InterruptedException {
		this.myInputSplit = (MyMaxValueInputSplit)split ;
	}

	@Override
	public boolean nextKeyValue() throws IOException, InterruptedException {
		if( this.key == null ){
			this.key = new IntWritable(this.myInputSplit.getS_index()) ;
			this.value = this.myInputSplit.getInt_array();
			return true ;
		}
		return false;
	}

	@Override
	public IntWritable getCurrentKey() throws IOException, InterruptedException {
		return key;
	}

	@Override
	public ArrayWritable getCurrentValue() throws IOException,
			InterruptedException {
		return value;
	}

	@Override
	public float getProgress() throws IOException, InterruptedException {
		if( this.key == null ){
			return 0 ; //0%
		}
		return 1; //100%
	}

	@Override
	public void close() throws IOException {
		
	}

}
