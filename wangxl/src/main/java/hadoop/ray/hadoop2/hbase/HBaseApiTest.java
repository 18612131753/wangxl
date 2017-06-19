package hadoop.ray.hadoop2.hbase;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.io.compress.Compression.Algorithm;
import org.junit.Before;
import org.junit.Test;

public class HBaseApiTest {
	
	private Configuration conf ;
	
	private static final String TABLE_NAME = "myuser";  
    private static final String CF_DEFAULT = "info";  
    
    @Before
	public void init() {
		conf = HBaseConfiguration.create();
		conf.set("hbase.rootdir", "hdfs://masterall:9000/hbase");
		conf.set("hbase.zookeeper.quorum", "masterall");
		conf.set("hbase.zookeeper.property.clientPort", "2181");
	}

    /**
	 * 获取表列表
     * @throws IOException 
	 * */
    @Test
    public void getTablesList( ) throws IOException {
    	 Connection connection = ConnectionFactory.createConnection(conf);  
         Admin admin = connection.getAdmin();
         TableName[] tablename = admin.listTableNames() ;
         for(TableName tn : tablename ){
        	 System.out.println( tn.getNameAsString() );
         }
    }
	/**
	 * 创建表
	 * */
    @Test
	public void createTables( ) throws IOException {  

        Connection connection = ConnectionFactory.createConnection(conf);  
        Admin admin = connection.getAdmin();  
        HTableDescriptor table = new HTableDescriptor( TableName.valueOf(TABLE_NAME ));  
        //setCompressionType 压缩格式
        table.addFamily( new HColumnDescriptor(CF_DEFAULT).setCompressionType(Algorithm.NONE));
        System.out.print("table addFamily ... ");
        TableName tableName = table.getTableName(); 
        //表是否存在
        if ( admin.tableExists(tableName) ) {
        	//如果存在，设置为可用
        	if(admin.isTableDisabled(tableName)){  
                //如果表不可用，先置为可用  
                admin.enableTable(tableName);  
            }  
            admin.disableTable(tableName);  
            System.out.print("Table exists, delete it first... ");  
            admin.deleteTable(tableName);  
            System.out.print("Table deleted, Done. ");  
        }  
        admin.createTable(table); 
        System.out.println(" Done.");  
   
        admin.close();
    }

}
