package hadoop.ray.hadoop2.mapreduce.s1_child;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

//Text,IntWritable,Text,IntWritable
//前两个入参类型 ， 后两个出参类型
public class SQLChildMap extends Mapper<Object, Text, Text, Text> {
	
	/**
	 * Object key : 每行文件的偏移量
	 * Text value ： 每行文件的内容
	 * Context context : Map端上下文
	 * 1,zhang,2
	 * */
	public void map(Object key, Text value, Context context ) throws IOException, InterruptedException {
		String[] v = value.toString().split(",");
		//1 zhang 2
		Text k_text = new Text();
		k_text.set(v[0]);
		Text r_text = new Text();
		r_text.set(  v[1]+"_r" );
		context.write( k_text , r_text);
		
		Text k_text_l = new Text();
		k_text_l.set(v[2]);
		Text r_text_l = new Text();
		r_text_l.set(  v[1]+"_l" );
		context.write( k_text_l , r_text_l );
		
	}
	
}