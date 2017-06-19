package hadoop.ray.hadoop2.mapreduce.a2_wordtop;

import org.apache.hadoop.io.IntWritable;

public class WordTop {
	private IntWritable num ;
	private String text ;
	
	
	public WordTop(IntWritable num, String text) {
		super();
		this.num = num;
		this.text = text;
	}

	public IntWritable getNum() {
		return num;
	}
	
	public void setNum(IntWritable num) {
		this.num = num;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	
}
