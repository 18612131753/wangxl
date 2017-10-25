package ali.rule.r2.a6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileReadTest {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		FileReadTest f =new  FileReadTest();
		f.trywithresource(); 
	}

	//简化了关闭流的代码
	public void trywithresource(){
		File srcFile = new File("D://work//gitspace//wangxl//src//main//java//ali//rule//r2//a6//a.txt");
		try ( BufferedReader in = new BufferedReader(new FileReader(srcFile)); ){
			String line = null;
			while ((line = in.readLine()) != null) {
				System.out.println( line );
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//当程序运行离开try语句块时，in 会被自动关闭。
	}
	
	//复杂写法
	public void testReadFile(){
		File srcFile = new File("D://work//gitspace//wangxl//src//main//java//ali//rule//r2//a6//a.txt");
		BufferedReader in = null;

		try {
			in = new BufferedReader(new FileReader(srcFile));
			String line = null;
			while ((line = in.readLine()) != null) {
				System.out.println( line );
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
