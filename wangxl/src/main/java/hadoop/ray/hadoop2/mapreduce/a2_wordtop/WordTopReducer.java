package hadoop.ray.hadoop2.mapreduce.a2_wordtop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class WordTopReducer extends Reducer<Text,IntWritable,String,IntWritable>{

	public static final int K = 5;
	private List<WordTop> list=new ArrayList<WordTop>();
	
	/**
	 * Text key : Map端输出的key值
	 * Iterable<IntWritable> value ： Map端的values集合
	 * Context context : Reducer端上下文
	 * */
	protected void reduce(Text key, Iterable<IntWritable> values, Context context)
			throws IOException, InterruptedException {
		int sum=0;
		for( IntWritable val : values ){
			sum += val.get();
		}
		IntWritable result = new IntWritable();
		result.set(sum);
		list.add( new WordTop(result,key.toString()) );
	}
	
	//cleanup方法则是在reduce方法运行完之后最后执行的
	@Override  
	protected void cleanup(Context context) throws IOException, InterruptedException  {
		TopComparator comparator=new TopComparator();
		Collections.sort(list, comparator);
		for( int i=0;i<K;i++){
			context.write( list.get(i).getText() , list.get(i).getNum());
		}
	}
	
	//从大到小排序
	class TopComparator implements Comparator<WordTop>{
	    public int compare(WordTop i1,WordTop i2) {
	        return i2.getNum().get()-i1.getNum().get();
	    }
	}
}
