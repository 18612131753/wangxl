package ali.rule.r2.a7;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class TryCatchReturnTest {

	public static void main(String[] args) {
		TryCatchReturnTest t = new TryCatchReturnTest();
		String s = t.test();
		System.out.println( s );
	}

	public String test(){
		File srcFile = new File("D://work//gitspace//wangxl//src//main//java//ali//rule//r2//a6//a.txt");
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(srcFile));
			String line = null;
			while ((line = in.readLine()) != null) {
				System.out.println( line );
			}
			System.out.println( "OK" );
			return "OK";  //这句不再执行
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			System.out.println( "finally" );
			return "finally";
		}
	}
}
