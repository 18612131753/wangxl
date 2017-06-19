package hadoop.ray.hadoop2.hbase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.RegexStringComparator;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.util.Bytes;

public class HbaseTest {
	static Configuration config = null;
	static {
		config = HBaseConfiguration.create();
		config.set("hbase.rootdir", "hdfs://masterall:9000/hbase");
		config.set("hbase.zookeeper.quorum", "masterall");
		config.set("hbase.zookeeper.property.clientPort", "2181");
	}

	public static void main(String[] args) throws Exception {
		HbaseTest ht = new HbaseTest();
		//1. 创建表
		//ht.createTable("htest", new String[] { "fcol1", "fcol2" });
		//2. 预分配region
//		ht.createTableSplit("htest", new String[]{"fcol1","fcol2"});
//		ht.createTableSplit2("htest", new String[]{"fcol1","fcol2"});
//		ht.deleteTable("htest");
		
		//3. 插入100条数据
//		for (int i = 0; i < 100; i++) {
//			ht.insertData("htest", "a"+i, "fcol1", "c1", "aaa"+i);
//			ht.insertData("htest", "a"+i, "fcol1", "c2", "bbb"+i);
//		}
//		ht.deleteData("htest", "a1");

//		ht.queryAll("htest");
		ht.queryByRowKey("htest", "a1");
//		List<String> list=new ArrayList<String>();
//		list.add("fcol1,c1,aaa1");
//		list.add("fcol1,c1,aaa2");
//		list.add("fcol1,c1,aaa3");
//		HbaseTest.selectByFilter("htest",list );
	}
	
	public void createTable(String table_name, String[] familys) {
		Admin admin ;
		try {
			Connection connection = ConnectionFactory.createConnection(config);  
	        admin = connection.getAdmin();
	        HTableDescriptor table = new HTableDescriptor( TableName.valueOf( table_name ));
	        TableName tableName = table.getTableName(); 
			if (admin.tableExists( tableName ) ) {
				System.out.println(tableName.getNameAsString()
						+ " is already exists,Please create another table!");
			} else {
				HTableDescriptor desc = new HTableDescriptor(tableName);
				for (int i = 0; i < familys.length; i++) {
					HColumnDescriptor family = new HColumnDescriptor(familys[i]);
					desc.addFamily(family);
				}
				admin.createTable(desc);
				System.out.println("Create table \'" + tableName + "\' OK!");
			}
			admin.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//预分配region
	public void createTableSplit(String table_name , String[] familys) {
		try {
			Connection connection = ConnectionFactory.createConnection(config);  
			Admin admin = connection.getAdmin();
			HTableDescriptor table = new HTableDescriptor( TableName.valueOf( table_name ));
	        TableName tableName = table.getTableName(); 
			if (admin.tableExists(tableName)) {
				System.out.println( tableName.getNameAsString()
						+ " is already exists,Please create another table!");
			} else {
				for (int i = 0; i < familys.length; i++) {
					HColumnDescriptor family = new HColumnDescriptor(familys[i]);
					table.addFamily(family);
				}
				//key a0 -a10000的可以，预先生成3个region
				admin.createTable(table, "a0".getBytes(), "a10000".getBytes(), 3);
				System.out.println("Create table \'" + tableName + "\' OK!");
			}
			admin.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void createTableSplit2(String table_name , String[] familys) {
		try {
			Connection connection = ConnectionFactory.createConnection(config);  
			Admin admin = connection.getAdmin();
			HTableDescriptor table = new HTableDescriptor( TableName.valueOf( table_name ));
	        TableName tableName = table.getTableName(); 
			if (admin.tableExists(tableName)) {
				System.out.println(tableName
						+ " is already exists,Please create another table!");
			} else {
				HTableDescriptor desc = new HTableDescriptor(tableName);
				for (int i = 0; i < familys.length; i++) {
					HColumnDescriptor family = new HColumnDescriptor(familys[i]);
					desc.addFamily(family);
				}
				byte[][] regions = new byte[][] { Bytes.toBytes("a3333333"), Bytes.toBytes("a6666666") };
				// 表示有三个region分别放入key：
				// [1] start key: , end key: a3333333
				// [2] start key:a3333333, end key: a6666666
				// [3] start key: a6666666, end key:
				admin.createTable( desc, regions);
				System.out.println("Create table \'" + tableName + "\' OK!");
			}
			admin.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteTable(String table_name ) {
		try {
			Connection connection = ConnectionFactory.createConnection(config);  
			Admin admin = connection.getAdmin();
			HTableDescriptor table = new HTableDescriptor( TableName.valueOf( table_name ));
	        TableName tableName = table.getTableName(); 
			if (!admin.tableExists(tableName)) {
				System.out.println(tableName + " is not exists!");
			} else {
				admin.disableTable(tableName);
				admin.deleteTable(tableName);
				System.out.println(tableName + " is delete!");
			}
			admin.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 向表中插入一条新数据
	 * @param tableName 表名
	 * @param rowKey 行键key
	 * @param family 列族
	 * @param qualifier 列名
	 * @param value 要插入的数据
	 * @throws IOException 
	 */
	public void insertData(
			String table_name , String rowKey, 
			String family,String qualifier,
			String value) throws IOException {
		Connection connection = ConnectionFactory.createConnection(config);
		//Admin admin = connection.getAdmin();
		Table table = connection.getTable( TableName.valueOf(table_name) );
		Put put = new Put( Bytes.toBytes(rowKey) );
		put.addColumn(
			Bytes.toBytes(family), 
			Bytes.toBytes(qualifier),
			Bytes.toBytes(value)
		);
		table.put(put);
		System.out.println("insert a data successful!");
		table.close();
		connection.close();
	}

	/**
	 * 根据rowKey删除数据
	 * */
	public void deleteData(String table_name, String rowKey) throws IOException {
		Connection connection = ConnectionFactory.createConnection(config);
		Table table = connection.getTable( TableName.valueOf(table_name) );
		Delete del = new Delete( Bytes.toBytes(rowKey) );
		table.delete(del);
		System.out.println("delete a data successful");
		table.close();
		connection.close();
	}

	/**
	 * 查询表的全部数据
	 * 
	 * */
	public void queryAll( String table_name ) throws IOException {
		Connection connection = ConnectionFactory.createConnection(config);
		Table table = connection.getTable( TableName.valueOf(table_name) );
		Scan scan = new Scan();
		scan.setStartRow("a1".getBytes());
		scan.setStopRow("a20".getBytes());
		ResultScanner scanner = table.getScanner(scan);
		for (Result row : scanner) {
			System.out.println( "\nRowkey: " + new String(row.getRow()) );
			for (Cell cell : row.rawCells()) {
				System.out.print(new String( cell.getRowArray()) + " " );
				System.out.print(new String( cell.getFamilyArray()) + ":" );
				System.out.print(new String( cell.getQualifierArray()) + " = ");
				System.out.print(new String( cell.getValueArray()));
				System.out.print(" timestamp = " + cell.getTimestamp() + "\n");
			}
		}
		table.close();
		connection.close();
	}

	public void queryByRowKey(String table_name , String rowKey ) throws IOException {
		Connection connection = ConnectionFactory.createConnection(config);
		Table table = connection.getTable( TableName.valueOf(table_name) );
		Get get = new Get(rowKey.getBytes());
		Result row = table.get(get);
		for (Cell cell : row.listCells()) {
			//System.out.println( cell.toString() );
			//System.out.print(new String( cell.getRow()) +" ");
			//System.out.print(new String( cell.getFamily()) + ":" );
			//System.out.print(new String( cell.getQualifier()) + " = ");
			//System.out.print(new String( cell.getValue()));
			//System.out.print(" timestamp = " + cell.getTimestamp() + "\n");
			System.out.println("===" + new String( cell.getRowArray())  );
			System.out.println("===" + new String( cell.getFamilyArray())  );
			System.out.println("===" + new String( cell.getQualifierArray()));
			System.out.println("===" + new String( cell.getValueArray()));
		}
		table.close();
		connection.close();
	}
	
	/**
	 * 根据过滤器筛选数据
	 * 列族，列，value
	 * List<String> list=new ArrayList<String>();
	 * list.add("fcol1,c1,aaa1");
	 * list.add("fcol1,c1,aaa2");
	 * list.add("fcol1,c1,aaa3");
	 * HbaseTest.selectByFilter("htest",list );
	 * */
	public static void selectByFilter(String table_name, List<String> arr) throws IOException {
		Connection connection = ConnectionFactory.createConnection(config);
		Table table = connection.getTable( TableName.valueOf(table_name) );
		//至少通过一条条件
		FilterList filterList = new FilterList( FilterList.Operator.MUST_PASS_ONE);
		Scan s1 = new Scan();
		for (String v : arr) {
			String[] s = v.split(",");
			filterList.addFilter(
				new SingleColumnValueFilter(
						Bytes.toBytes(s[0]), //列族
						Bytes.toBytes(s[1]), //列
						CompareOp.EQUAL,
						Bytes.toBytes(s[2])
				)
			);
			// 添加下面这一行后，则只返回指定的cell，同一行中的其他cell不返回
			// s1.addColumn(Bytes.toBytes(s[0]), Bytes.toBytes(s[1]));
		}
		s1.setFilter(filterList);
		// SingleColumnValueFilter 用于测试列值相等 (CompareOp.EQUAL ), 不等
		// (CompareOp.NOT_EQUAL),或范围 (e.g., CompareOp.GREATER).
		// 下面示例检查列值和字符串'values' 相等...
		// SingleColumnValueFilter f = new
		// SingleColumnValueFilter(Bytes.toBytes("cFamily"),
		// Bytes.toBytes("column"), CompareFilter.CompareOp.EQUAL,
		// Bytes.toBytes("values"));
		// SingleColumnValueFilter f = new
		// SingleColumnValueFilter(Bytes.toBytes("cFamily"),
		// Bytes.toBytes("column"), CompareFilter.CompareOp.EQUAL,new
		// SubstringComparator("values"));
		// s1.setFilter(f);
		// ColumnPrefixFilter 用于指定列名前缀值相等
		// ColumnPrefixFilter f = new
		// ColumnPrefixFilter(Bytes.toBytes("values"));
		// s1.setFilter(f);
		// MultipleColumnPrefixFilter 和 ColumnPrefixFilter 行为差不多，但可以指定多个前缀
		// byte[][] prefixes = new byte[][] {Bytes.toBytes("value1"),
		// Bytes.toBytes("value2")};
		// Filter f = new MultipleColumnPrefixFilter(prefixes);
		// s1.setFilter(f);
		// QualifierFilter 是基于列名的过滤器。
		// Filter f = new QualifierFilter(CompareFilter.CompareOp.EQUAL, new
		// BinaryComparator(Bytes.toBytes("col5")));
		// s1.setFilter(f);
		// RowFilter 是rowkey过滤器,通常根据rowkey来指定范围时，使用scan扫描器的StartRow和StopRow
		// 方法比较好。Rowkey也可以使用。
		Filter f = new RowFilter(CompareFilter.CompareOp.GREATER_OR_EQUAL, new RegexStringComparator(".*5$"));//正则获取结尾为5的行
		s1.setFilter(f);

		ResultScanner rs = table.getScanner(s1);
		for (Result rr = rs.next(); rr != null; rr = rs.next()) {
			for (Cell cell : rr.rawCells()) {
				//System.out.print(new String( cell.getRow()) +" ");
				//System.out.print(new String( cell.getFamily()) + ":" );
				//System.out.print(new String( cell.getQualifier()) + " = ");
				//System.out.print(new String( cell.getValue()));
				System.out.print(new String( cell.getRowArray()) + " " );
				System.out.print(new String( cell.getFamilyArray()) + ":" );
				System.out.print(new String( cell.getQualifierArray()) + " = ");
				System.out.print(new String( cell.getValueArray()));
				System.out.print(" timestamp = " + cell.getTimestamp() + "\n");
			}
		}
		
	}

	

	
}
