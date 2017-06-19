package hadoop.ray.hadoop2.mapreduce.s1_child;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

//Text,IntWritable,Text,IntWritable
//前两个入参类型 ， 后两个出参类型
public class SQLChildReduce extends Reducer<Text, Text ,Text,Text> {



	/**
	 * Text key : Map端输出的key值
	 * Iterable<IntWritable> value ： Map端的values集合
	 * Context context : Reducer端上下文
	 * 
	 * 1 zhang_r
	 * 2 zhang_l
	 *
	 * */
    public void reduce(Text key, Iterable<Text> values,  Context context ) throws IOException, InterruptedException {
    	Text l = new Text() ;
    	Text r = new Text() ;
      for (Text val : values) {
	        String value = val.toString() ;
	        if( value.indexOf("_l") >0 ){
	        	l.set(value); ;
	        } else if( value.indexOf("_r") >0 ){
	        	r.set(value);
	        }
      }
      if( l.getLength() > 0 && r.getLength() >0 ){
          context.write( l , r );
      }
    }
}
