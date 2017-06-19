package hadoop.ray.hadoop2.mapreduce.a6_friends;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


/**
 * 二度好友推荐
 * */
public class Deg2friend {

	public static class Mapper1 extends Mapper<Object, Text, Text, Text> {
		public void map(Object key, Text value, Context context)
				throws IOException, InterruptedException {
			String[] record = value.toString().split(" ") ;
			String person = record[0] ;
			String[] p_friend = record[1].split(",") ;
			//A B,H
			//A B F1,F2,F2
			//A H F1
			//一度好友
			for(String friend : p_friend ){
				//返回1表示大于，返回-1表示小于，返回0表示相等。按字母先后顺序排序，字符小的在前
				if( friend.compareTo(person) > 0 ){
					context.write( new Text(person +" "+friend), new Text("f1") );
				} else if( friend.compareTo(person) < 0 ){
					context.write( new Text(friend+" "+person), new Text("f1") );
				}
			}
			//二度好友
			for(int i=0;i<p_friend.length-1 ; i++ ){ //B,H
				for(int j=i+1;j<p_friend.length ; j++ ){ //B,H
					if( p_friend[i].compareTo( p_friend[j] ) < 0 ){
						context.write( new Text(p_friend[i]+" "+p_friend[j]), new Text("f2") );
					} else if( p_friend[i].compareTo( p_friend[j] ) > 0 ){
						context.write( new Text(p_friend[j]+" "+p_friend[i]), new Text("f2") );
					}
				}
			}
		}
	}

	//key = fri_k,v  即A B
	//value f1,f2,f1,f2...
	public static class Reducer1 extends Reducer<Text, Text, Text, IntWritable> {
		public void reduce(Text key, Iterable<Text> values, Context context)
				throws IOException, InterruptedException {
			int deg = 0 ; //推荐度，值越高共同好友就越多
			for( Text v : values ){
				String value = v.toString() ;
				if( value.equals("f1") ){
					deg = -1;
					break;
				}
				if( value.equals("f2") ){
					deg ++;
				}
			}
			if( deg > 0 ){
				context.write( key, new IntWritable(deg) );
			}
		}
	}

	//A C	1  -->A C	推荐度 
	public static class Mapper2 extends Mapper<Object, Text, Text, Text> {
		public void map(Object key, Text value, Context context)
				throws IOException, InterruptedException {
			String[] record = value.toString().split("\\t") ;
			String[] person = record[0].split(" ");
			context.write( new Text(person[0]) , new Text(person[1]+"_"+record[1]) );
			context.write( new Text(person[1]) , new Text(person[0]+"_"+record[1]) );
		}
	}
	//A  {C_1,D_1}
	public static class Reducer2 extends Reducer<Text, Text, Text, Text> {
		public void reduce(Text key, Iterable<Text> values, Context context)
				throws IOException, InterruptedException {
			List<Deg> list = new ArrayList<Deg>();
			//遍历2度好友
			for(Text f2 : values ){
				String[] friend = f2.toString().split("_");
				Deg deg = new Deg( friend[0] , Integer.valueOf(friend[1]) );
				list.add(deg);
			}
			DegComparator comparator = new DegComparator();
			Collections.sort(list, comparator);
			StringBuffer friend_deg = new StringBuffer();
			for( Deg deg : list ){
				friend_deg.append(deg.getFriend()+"_"+deg.getDeg()+" ");
			}
			context.write(key, new Text(friend_deg.toString()));
		}
	}
	
	public static class Deg{
		
		private int deg=0;  //推荐度
		private String friend ; //好友名称
		
		public Deg(){}
		public Deg( String friend , int deg ){
			this.deg = deg ;
			this.friend = friend ;
		}
		public int getDeg() {
			return deg;
		}
		public String getFriend() {
			return friend;
		}
	}
	
	//从推荐度大到小排序,-1代表前者小,0代表两者相等,1代表前者大
	//排序就是比较谁大谁小，将小的放在前面，大的放在后面
	// ['kɒmpəreɪtə]
	public static class DegComparator implements Comparator<Deg>{
	    public int compare(Deg i1,Deg i2) {
	        return i2.getDeg()-i1.getDeg();
	    }
	}
	
	public static void main(String[] args) throws Exception {
		
		String hdfs = "hdfs://masterall:9000" ;
		
		Configuration conf = new Configuration();
		conf.set( FileSystem.FS_DEFAULT_NAME_KEY , hdfs );
		//hadoop2要添加这两句
		conf.set("mapreduce.framework.name", "yarn");  
		conf.set("yarn.resourcemanager.address", "masterall:8032");
		conf.set("mapred.jar","D:\\boxlog\\wordfriend.jar"); 
				
		Path input1 = new Path( hdfs + "/mr_friend/input");
		Path out1 = new Path( hdfs + "/mr_friend/output1");
		Path input2= out1 ;
		Path out2= new Path( hdfs + "/mr_friend/output2" );

		//true ，递归删除
		out1.getFileSystem(conf).delete(out1,true);
		out2.getFileSystem(conf).delete(out2,true);

		Job job = Job.getInstance(conf , "deg2friends") ;
		job.setJarByClass(Deg2friend.class);
		
	    job.setMapperClass(Mapper1.class);
		job.setReducerClass(Reducer1.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		FileInputFormat.addInputPath(job, input1);
		FileOutputFormat.setOutputPath( job, out1 );
		job.waitForCompletion(true) ;

		Job job2 = Job.getInstance(conf , "deg2friends") ;
		
		job2.setJarByClass(Deg2friend.class);
	    job2.setMapperClass(Mapper2.class);
	    job2.setReducerClass(Reducer2.class);
	    job2.setMapOutputKeyClass(Text.class);
	    job2.setMapOutputValueClass(Text.class);
	    job2.setOutputKeyClass(Text.class);
	    job2.setOutputValueClass(Text.class);
	    
		FileInputFormat.addInputPath(job2, input2);
		FileOutputFormat.setOutputPath( job2, out2 );
		job2.waitForCompletion(true) ;
		System.exit(1);
	}
}
