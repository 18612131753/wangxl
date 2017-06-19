package hadoop.ray.hadoop2.hive.jdbc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.security.sasl.AuthenticationException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hive.service.auth.PasswdAuthenticationProvider;
 
/**
 * hive自动校验
 * 打包后放到hive/lib下
 * <property>
    <name>hive.server2.custom.authentication.class</name>
    <value>hadoop.ray.hadoop2.hive.jdbc.JDBCHiveServer2Auth</value>
	</property>
	<property>
    <name>hive.server2.custom.authentication.file</name>
    <value>/home/hadoop/hive/conf/password</value>
	</property>
	
	password:
	user1:password1
	user2:password2
 * */
public class JDBCHiveServer2Auth implements PasswdAuthenticationProvider{

	@Override
	public void Authenticate(String user, String password) throws AuthenticationException {
		boolean ok = false;
        HiveConf hiveConf = new HiveConf();
        Configuration conf = new Configuration(hiveConf);
        //获取配置文件
        String filePath = conf.get("hive.server2.custom.authentication.file");
        File file = new File(filePath);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
            	//-1表示每个分割的字段都要，a,b,c,,, 没有-1 ，则长度为3 ，有-1 ，长度为6
                String[] datas = tempString.split(":", -1);
                if(datas.length != 2) continue;
                //ok
                if(datas[0].equals( user ) && datas[1].equals(password)) {
                	//验证成功
                    ok = true;
                    break;
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new AuthenticationException("read auth config file error, [" + filePath + "] ..", e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {}
            }
        }
        if( ok ) {
            System.out.println("user [" + user + "] auth check ok .. ");
        } else {
            System.out.println("user [" + user + "] auth check fail .. ");
            throw new AuthenticationException("user [" + user + "] auth check fail .. ");
        }
	}

}
