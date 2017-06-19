package hadoop.ray.hadoop2.mapreduce.a1_wordcount;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

//Text,IntWritable,Text,IntWritable
//前两个入参类型 ， 后两个出参类型
public class WorkCountMap extends Mapper<Object, Text, Text, IntWritable> {

	private final static IntWritable one = new IntWritable(1);
	private Text word = new Text();
	
	/**
	 * Object key : 每行文件的偏移量
	 * Text value ： 每行文件的内容
	 * Context context : Map端上下文
	 * hello word
	 * 
	 * hello 1
	 * word 1
	 * */
	public void map(Object key, Text value, Context context ) throws IOException, InterruptedException {
		//java默认的分隔符是“空格”、“制表符（‘\t’）”、“换行符(‘\n’）”、“回车符（‘\r’）”。
		StringTokenizer itr = new StringTokenizer(value.toString());
		while (itr.hasMoreTokens()) {
			word.set(itr.nextToken());
			context.write(word, one);
		}
	}
	
}