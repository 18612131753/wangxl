package hadoop.ray.hadoop2.mapreduce.a3_myinputformat;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.InputSplit;

public class MyMaxValueInputSplit extends InputSplit implements Writable{

	private int s_index ;  //数组下标开始位置
	private int e_index ;  //数组下标结束位置
	private ArrayWritable int_array = new ArrayWritable(IntWritable.class);  //数据
	
	public MyMaxValueInputSplit(){}
	
	public MyMaxValueInputSplit(int s_index , int e_index ){
		this.s_index = s_index ;
		this.e_index = e_index ;
		IntWritable[] data = new IntWritable[e_index-s_index];
		for( int i=0;i<e_index-s_index;i++ ){
			//赋值，将传过来的分片数据赋值到本对象
			data[i] = MyMaxValueInputFormat.values[s_index+i] ;
		}
		this.int_array = new ArrayWritable(IntWritable.class);
		this.int_array.set( data );
	}
	
	@Override
	public long getLength() throws IOException, InterruptedException {
		return this.e_index - this.s_index;
	}

	@Override
	//获取分片的位置列表，hdfs因为保存多份，所以返回是一个数组
	public String[] getLocations() throws IOException, InterruptedException {
		return new String[]{ "s1","s2" };
	}
	
	@Override
	//序列化
	public void write(DataOutput out) throws IOException {
		out.writeInt(this.s_index);
		out.writeInt(this.e_index);
		this.int_array.write(out);
	}

	@Override
	//反序列化
	public void readFields(DataInput in) throws IOException {
		this.s_index = in.readInt();
		this.e_index = in.readInt();
		this.int_array.readFields(in);
	}

	public int getS_index() {
		return s_index;
	}

	public void setS_index(int s_index) {
		this.s_index = s_index;
	}

	public int getE_index() {
		return e_index;
	}

	public void setE_index(int e_index) {
		this.e_index = e_index;
	}

	public ArrayWritable getInt_array() {
		return int_array;
	}

	public void setInt_array(ArrayWritable int_array) {
		this.int_array = int_array;
	}

}
