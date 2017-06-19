package hadoop.ray.hadoop2.mapreduce.a1_wordcount;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

//Text,IntWritable,Text,IntWritable
//前两个入参类型 ， 后两个出参类型
public class WorkCountReduce extends Reducer<Text,IntWritable,Text,IntWritable> {

	private IntWritable result = new IntWritable();

	/**
	 * Text key : Map端输出的key值
	 * Iterable<IntWritable> value ： Map端的values集合
	 * Context context : Reducer端上下文
	 * 
	 * hello 1,1,1,1,1
	 *
	 * */
    public void reduce(Text key, Iterable<IntWritable> values,  Context context ) throws IOException, InterruptedException {
      int sum = 0;
      for (IntWritable val : values) {
        sum += val.get();
      }
      result.set(sum);
      context.write(key, result);
    }
}
