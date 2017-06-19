package hadoop.ray.hadoop2.mapreduce.a7_bfs;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * 最短路径 -BFS广度优先搜索
 * */
public class BFS {

	public static int maxRoad = 99999999 ; //最短路径
	
	public static class Mapper1 extends Mapper<Object, Text, Text, Text> {
		
		// A B-10,D-5  -->   A 0 B-10,D-5
		//                   B 10
		//                   D 5
		public void map(Object key, Text value, Context context)
				throws IOException, InterruptedException {
			String node_from = context.getJobName().split("_")[0];
			String[] record = value.toString().split(" ");
			String node_source = record[0] ;
			if( node_from.equals( node_source ) ){
				context.write( new Text(node_source) ,  new Text(0+" "+record[1]) );
			} else {
				context.write( new Text(node_source) ,  new Text(BFS.maxRoad+" "+record[1]) );
			}
		}
	}

	public static class Reducer1 extends Reducer<Text, Text, Text, Text> {
		public void reduce(Text key, Iterable<Text> values, Context context)
				throws IOException, InterruptedException {
			//如果有多行，则取第一行
			Iterator<Text> it = values.iterator() ;
			if( it.hasNext() ){
				Text firstRecode = it.next() ;
				context.write( new Text(key) , firstRecode );
			}
		}
	}

	public static class Mapper2 extends Mapper<Object, Text, Text, Text> {

		// A	0 B-10,D-5 -->A	  0 B-10,D-5
		//                 -->A	  B 10
		//                 -->A   D 5 
		public void map(Object key, Text value, Context context)
				throws IOException, InterruptedException {
			String[] record = value.toString().split("\\t"); //A	  0 B-10,D-5
			String[] dist_nodes = record[1].split(" ");      //0 B-10,D-5
			
			int node_dist = Integer.valueOf( dist_nodes[0] ) ;  //到原节点距离   0
			String[] node_list = dist_nodes[1].split(",") ;     //关联节点           B-10,D-5
			
			for( String node : node_list ){
				String[] child = node.split("-");  //B-10
				String child_name = child[0] ;     //B
				int child_dist = Integer.valueOf( child[1] );//10
				//如果是最短路径
				if( node_dist >= BFS.maxRoad ){
					context.write( new Text(child_name), new Text( String.valueOf(BFS.maxRoad) ) );
				} else {
					context.write( new Text(child_name), new Text( String.valueOf(child_dist+ node_dist)) );
				}
			}
			context.write( new Text(record[0]) , new Text(record[1]) );
		}
	}

	//A	 { 0(MAX) B-10,D-5  ,  0  ,   9999 } 
	public static class Reducer2 extends Reducer<Text, Text, Text, Text> {
		public void reduce(Text key, Iterable<Text> values, Context context)
				throws IOException, InterruptedException {
			int min_dist = BFS.maxRoad ;  //新的距离中，最小的路径
			String nodes = "";
			Iterator<Text> it = values.iterator() ;
			while( it.hasNext() ){
				String value = it.next().toString() ;
				if( value.indexOf(" ") < 0 ){
					int dist = Integer.valueOf(value) ;
					if( dist < min_dist ){
						min_dist = dist ;
					}
				} else {
					String[] val = value.split(" ") ;
					int node_dist = Integer.valueOf(val[0]) ;
					nodes = val[1] ;
					if( node_dist < min_dist ){
						min_dist = node_dist ;
					}
				}
			}
			context.write( key , new Text( min_dist+" "+ nodes));
		}
	}

	public static void main(String[] args) throws Exception {
		
		String hdfs = "hdfs://masterall:9000" ;
		
		String sourceNode = "B_" ;  //源点,通过job那么传入给map/reduce
		int out_num = 0 ;
		
		Configuration conf = new Configuration();
		conf.set( FileSystem.FS_DEFAULT_NAME_KEY , hdfs );
		//hadoop2要添加这两句  // conf.set("mapred.job.tracker", "master:9001");
		conf.set("mapreduce.framework.name", "yarn");  
		conf.set("yarn.resourcemanager.address", "masterall:8032");
		conf.set("mapred.jar","D:\\boxlog\\worddfs.jar");
		
		Path input = new Path(hdfs + "/mr_bfs/input");
		Path out = new Path( hdfs + "/mr_bfs/out"+out_num);
		out.getFileSystem(conf).delete(out,true);
		//true ，递归删除
		Job job = Job.getInstance(conf , "bfs") ;
		job.setJobName( sourceNode + out_num );
	    job.setNumReduceTasks(1);
	    job.setMapperClass(Mapper1.class);
		job.setReducerClass(Reducer1.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		FileInputFormat.addInputPath(job, input);
		FileOutputFormat.setOutputPath( job, out );
		job.waitForCompletion(true) ;
		
		//走4次之后
		for( int i=0;i<4;i++){
			input = out ;
			out_num++ ;
			out = new Path( hdfs + "/mr_bfs/out"+out_num);
			out.getFileSystem(conf).delete(out,true);
			Job job1 = Job.getInstance( conf , "bfs"+out_num ) ;
			job1.setJobName( sourceNode + out_num );
			job1.setJarByClass(BFS.class);
		    job1.setNumReduceTasks(1);
		    job1.setMapperClass(Mapper2.class);
			job1.setReducerClass(Reducer2.class);
			job1.setMapOutputKeyClass(Text.class);
			job1.setMapOutputValueClass(Text.class);
			job1.setOutputKeyClass(Text.class);
			job1.setOutputValueClass(Text.class);
			FileInputFormat.addInputPath(job1, input);
			FileOutputFormat.setOutputPath( job1, out );
			job1.waitForCompletion(true) ;
		}
	}
}
