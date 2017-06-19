package hadoop.ray.hadoop2.mapreduce.a2_wordtop;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WordTopMapper extends Mapper<Object,Text,Text,IntWritable>{

	private final static IntWritable one = new IntWritable(1);
	private Text key_word = new Text();
	
	/**
	 * Object key : 每行文件的偏移量
	 * Text value ： 每行文件的内容
	 * Context context : Map端上下文
	 * */
	public void map(Object key , Text value , Context context )
		throws IOException , InterruptedException{
		String[] word = value.toString().split(" ");
		for( int i=0 ;i<word.length;i++){
			if( word[i].length() >0 ){
				key_word.set( word[i] );
				context.write( key_word , one );
			}
		}
	}
}