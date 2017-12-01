package ali.rule.r1.serializable;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 部分字段不可序列化
 * */
public class TransientTest {
	 
    public static void main(String[] args) {
 
        User user = new User();
        user.setUsername("Alexia");
        user.setPasswd("123456");
 
        System.out.println("read before Serializable: ");
        System.out.println("username: " + user.getUsername());
        System.err.println("password: " + user.getPasswd());
 
        try {
            ObjectOutputStream os = new ObjectOutputStream(
                    new FileOutputStream("D:/user.txt"));
            os.writeObject(user); // 将User对象写进文件
            os.flush();
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(
                    "D:/user.txt"));
            user = (User) is.readObject(); // 从流中读取User的数据
            is.close();
 
            System.out.println("\nread after Serializable: ");
            System.out.println("username: " + user.getUsername());
            System.err.println("password: " + user.getPasswd());
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
 
class User implements Serializable {
    private static final long serialVersionUID = 8294180014912103005L;  
 
    private String username;
    private transient String passwd;
 
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getPasswd() {
        return passwd;
    }
 
    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
 
}
