package gof.ray.Adapter.myio;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 适配器模式
 * InputStreamReader中拥有inputStream
 * 
 * InputStream ： 是所有字节输入流的超类
 * InputStreamReader ： 是字节流与字符流之间的桥梁，能将字节流输出为字符流，并且能为字节流指定字符集，可输出一个个的字符；
 */
public class IO {

    public static void main(String[] args) throws IOException {
        File file = new File(".\\src\\main\\java\\gof\\ray\\Adapter\\myio\\Hello1.txt");

        InputStream is = new FileInputStream(file ) ;
        byte b[] = new byte[50] ;        // 所有的内容都读到此数组之中
        is.read(b) ;        // 读取内容   网络编程中 read 方法会阻塞
        is.close() ;  //关闭输出流  
        System.out.println("字节流内容为：" + new String(b)) ;
        
        file = new File(".\\src\\main\\java\\gof\\ray\\Adapter\\myio\\Hello1.txt");
        InputStreamReader isr = new InputStreamReader( new FileInputStream(file ) );
        char c[] = new char[50] ; 
        isr.read(c);
        isr.close();
        System.out.println("字符流内容为：" + new String(c)) ;
    }

}


