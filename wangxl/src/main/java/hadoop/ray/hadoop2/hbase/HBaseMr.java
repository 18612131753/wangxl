package hadoop.ray.hadoop2.hbase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.hbase.mapreduce.TableReducer;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;

/**
 * 需求
 * 建立表‘word’，包含一个列族‘content’
 * 向表中添加数据，在列族中放入列‘info’，并将短文数据放入该列中，如此插入多行，行键为不同的数据即可
 * 建立表‘stat’，包含一个列族‘content’
 * 通过Mr操作Hbase的‘word’表，对‘content：info’中的短文做词频统计，并将统计结果写入‘stat’表的‘content：info中’，行键为单词
 * 
 * HBASE命令：
 * scan "stat"  
 * get 'stat', 'Hadoop','content:info'
 * */
public class HBaseMr {
	static Configuration config = null;
	static {
		config = HBaseConfiguration.create();
		config.set("hbase.rootdir", "hdfs://masterall:9000/hbase");
		config.set("hbase.zookeeper.quorum", "masterall");
		config.set("hbase.zookeeper.property.clientPort", "2181");
		config.set("mapreduce.framework.name", "yarn");  
		config.set("yarn.resourcemanager.address", "masterall:8032");
		config.set("mapred.jar","D:\\boxlog\\HBaseMr.jar"); 
	}
	public static final String tableName = "word";
	public static final String colf = "content"; //列簇
	public static final String col = "info";     //列
	public static final String tableName2 = "stat";
	/**
	 * TableMapper<Text,IntWritable> Text:输出的key类型，IntWritable：输出的value类型
	 */
	public static class MyMapper extends TableMapper<Text, IntWritable> {
		private static IntWritable one = new IntWritable(1);
		private static Text word = new Text();

		@Override
		//key为hbase的rowkey
		protected void map(ImmutableBytesWritable key, Result value,
				Context context) throws IOException, InterruptedException {
			// 表里面只有一个列族，所以我就直接获取每一行的值
			String words = Bytes.toString(value.getValue(Bytes.toBytes(colf), Bytes.toBytes(col)));
			String itr[] = words.toString().split(" ");
			for (int i = 0; i < itr.length; i++) {
				word.set(itr[i]);
				context.write(word, one);
			}
		}
	}
	/**
	 * TableReducer<Text,IntWritable>
	 * Text:输入的key类型，IntWritable：输入的value类型，ImmutableBytesWritable：输出类型
	 */
	public static class MyReducer extends TableReducer<Text, IntWritable, ImmutableBytesWritable> {
		@Override
		protected void reduce(Text key, Iterable<IntWritable> values,
				Context context) throws IOException, InterruptedException {
			int sum = 0;
			for (IntWritable val : values) {
				sum += val.get();
			}
			// 添加一行记录，每一个单词作为行键
			Put put = new Put(Bytes.toBytes(key.toString()));
			// 在列族content中添加一个列info,赋值为每个单词出现的次数
			// String.valueOf(sum)先将数字转化为字符串，否则存到数据库后会变成\x00\x00\x00\x这种形式
			// 然后再转二进制存到hbase。
			put.addColumn(Bytes.toBytes(colf), Bytes.toBytes(col), Bytes.toBytes(String.valueOf(sum)));
			context.write(
					new ImmutableBytesWritable(Bytes.toBytes(key.toString())),
					put);
		}
	}

	public static void initTB() throws IOException {
		Connection connection = ConnectionFactory.createConnection(config);  
		Admin   admin = connection.getAdmin();
		Table table = connection.getTable( TableName.valueOf(tableName) );
        TableName tableName = table.getName();
        Table table2 = connection.getTable( TableName.valueOf(tableName2) );
        TableName tableName2 = table2.getName(); 
        
		if (admin.tableExists(tableName) || admin.tableExists(tableName2)) {
			System.out.println("table is already exists!");
			admin.disableTable(tableName);
			admin.deleteTable(tableName);
			admin.disableTable(tableName2);
			admin.deleteTable(tableName2);
		}
		HTableDescriptor tableDes= new HTableDescriptor( tableName );
		HColumnDescriptor family = new HColumnDescriptor(colf);
		tableDes.addFamily(family);
		admin.createTable(tableDes);
		
		HTableDescriptor tableDes2= new HTableDescriptor( tableName2 );
		HColumnDescriptor family2 = new HColumnDescriptor(colf);
		tableDes2.addFamily(family2);
		admin.createTable(tableDes2);
		
		List<Put> lp = new ArrayList<Put>();
		
		Put p1 = new Put(Bytes.toBytes("1"));
		p1.addColumn(
				colf.getBytes(), 
				col.getBytes(),
				("The Apache Hadoop software library is a framework").getBytes());
		lp.add(p1);
		
		Put p2 = new Put(Bytes.toBytes("2"));
		p2.addColumn(
				colf.getBytes(),
				col.getBytes(),
				("The common utilities that support the other Hadoop modules").getBytes());
		lp.add(p2);
		
		Put p3 = new Put(Bytes.toBytes("3"));
		p3.addColumn(
				colf.getBytes(), 
				col.getBytes(),
				("Hadoop by reading the documentation").getBytes());
		lp.add(p3);
		
		Put p4 = new Put(Bytes.toBytes("4"));
		p4.addColumn(
				colf.getBytes(), 
				col.getBytes(),
				("Hadoop from the release page").getBytes());
		lp.add(p4);
		
		Put p5 = new Put(Bytes.toBytes("5"));
		p5.addColumn(colf.getBytes(), col.getBytes(),
				("Hadoop on the mailing list").getBytes());
		lp.add(p5);
		table.put(lp);
		lp.clear();
		table.close();
	}
	public static void main(String[] args) throws Exception {
		initTB();
		Job job = Job.getInstance(config , "HBaseMr") ;
		Scan scan = new Scan();
		// 指定要查询的列族
		scan.addColumn(Bytes.toBytes(colf), Bytes.toBytes(col));
		// 指定Mapper读取的表为word
		TableMapReduceUtil.initTableMapperJob(
				tableName, scan, MyMapper.class,
				Text.class, IntWritable.class, job);
		// 指定Reducer写入的表为stat
		TableMapReduceUtil.initTableReducerJob(tableName2, MyReducer.class, job);
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}