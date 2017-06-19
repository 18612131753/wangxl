package ray.java.serializable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MainT {

	public static void main(String[] args) {

		try {
			//先写，再注释，然后执行单独读取，变换serialVersionUID抛异常
			//如果serialVersionUID不变化，类属性任意改变，不会影响反序列化
			FileOutputStream fos = new FileOutputStream(new File("E:\\tmp\\demo2.txt"));
			ObjectOutputStream os = new ObjectOutputStream(fos);
			AA s = new AA( 1 , "姓名");

			os.writeObject(s);
			os.flush();
			os.close();

			FileInputStream fis = new FileInputStream(new File("E:\\tmp\\demo2.txt"));
			ObjectInputStream ois = new ObjectInputStream(fis);

			AA s2 = (AA) ois.readObject();
			System.out.println(s2.toString());
			ois.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
