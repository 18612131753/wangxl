package gof.ray.Decorator.myio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;

/**
 * FileInputStream：以字节流方式读取,读取出来的是byte数组
 * FileReader：把文件转换为字符流读入,出来的是char数组或者String
 */
public class IO {

    public static void main(String[] args) throws IOException {
        File file = new File(".\\src\\main\\java\\gof\\ray\\Decorator\\myio\\Hello1.txt");
        // 创建文件
//        file.createNewFile();
//        FileWriter writer = new FileWriter(file); 
//        // 向文件写入内容
//        writer.write("This\n is\n an\n example\n"); 
//        writer.flush();
//        writer.close();
        char[] buffer = new char[1024];
        Reader fileReader = new FileReader( file ) ;
         
        int num = 0; 
        while((num = fileReader.read(buffer)) != -1 ){
            System.out.println( "FileReader:"+new String(buffer,0,num) ); 
        }
        fileReader.close();
        
        Reader bufferReader = new BufferedReader( new FileReader( file ) );
        while((num = bufferReader.read(buffer)) != -1 ){
            System.out.println( "BufferReader:"+new String(buffer,0,num) ); 
        }
        bufferReader.close();
        
        LineNumberReader lineNumberReader = new LineNumberReader( new BufferedReader( new FileReader( file ) ) );
        // Reader lineNumberReader = new LineNumberReader( new BufferedReader( new FileReader( file ) ) );
        String line ;
        while((line = lineNumberReader.readLine()) != null ){
            System.out.println( "LineNumberReader:"+ line ); 
        }
        lineNumberReader.close();

    }

}


