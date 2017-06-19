package hadoop.ray.hadoop2.mapreduce.a5_myoutputformat;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class MyMaxValueOutPutFormat
		extends TextOutputFormat<Text, IntWritable> {

	private MyMaxValueRecordWriter writer = null ; 
	
	public RecordWriter<Text, IntWritable> getRecordWriter(TaskAttemptContext job)
			throws IOException, InterruptedException {
		if (writer == null) {
			writer = new MyMaxValueRecordWriter( job , this.getTaskOutputPath(job) );
		}
		return writer;
	}
	
	//通过conf获取输出路径
	private Path getTaskOutputPath(TaskAttemptContext conf) throws IOException {
		Path workPath = super.getOutputPath(conf);
		if (workPath == null) {
			throw new IOException("Undefined job output-path");
		}
		return workPath;
	}
	/**
	 * 自定义RecordWriter，输出文件名为最大int数+“.txt”
	 * */
	public class MyMaxValueRecordWriter extends RecordWriter<Text, IntWritable>  {

		private TaskAttemptContext job = null  ;
		private Path outpath = null ;
		
		/** RecordWriter的缓存 */
		private HashMap<String, RecordWriter<Text, IntWritable>> recordWriters = null;
		
		public MyMaxValueRecordWriter( TaskAttemptContext job , Path outpath ){
			super();
			this.job = job ;
			this.outpath = outpath ;
			this.recordWriters = new HashMap<String, RecordWriter<Text, IntWritable>>();
		}
		
		//write(K key,V value)：实现如何写key/value
		@Override
		public void write(Text key, IntWritable value) throws IOException, InterruptedException {
			// 得到输出文件名
			String baseName = value.toString()+".txt" ;
			RecordWriter<Text, IntWritable> rw = this.recordWriters.get(baseName);
			if (rw == null) {
				Path file = new Path(this.outpath, baseName);
				FSDataOutputStream fileOut = 
						file.getFileSystem(this.job.getConfiguration()).create(file,true);
				//###表示可以与value的间隔是什么
				rw = new LineRecordWriter<Text, IntWritable>( fileOut , "###" );
				this.recordWriters.put( baseName, rw );
			}
			rw.write(key, value);
		}

		//close(Reporter reporter)：关闭操作
		@Override
		public void close(TaskAttemptContext context) throws IOException,
				InterruptedException {
			Iterator<RecordWriter<Text, IntWritable>> values = this.recordWriters.values()
					.iterator();
			while (values.hasNext()) {
				values.next().close(context);
			}
			this.recordWriters.clear();
		}
	}
}
