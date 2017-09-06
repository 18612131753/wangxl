package ali.rule.r1.serializable;

import java.io.Serializable;

public class AA implements Serializable {

	/** 
	 * serialVersionUID 是为了保持版本的兼容性，即在版本升级时反序列化仍保持对象的唯一性。
	 * 
	 * 如果没有设置这个值，你在序列化一个对象之后，改动了该类的字段或者方法名之类的，那如果你再反序列化想取出之前的那个对象时就可能会抛出异常，
	 * 因为你改动了类中间的信息，serialVersionUID是根据类名、接口名、成员方法及属性等来生成一个64位的哈希字段,
	 * 当修改后的类去反序列化的时候发现该类的serialVersionUID值和之前保存在问价中的serialVersionUID值不一致，所以就会抛出异常。  
	 * 只修改 serialVersionUID ，在反序列化时，也会报异常
	 * 而显示的设置serialVersionUID值就可以保证版本的兼容性，如果你在类中写上了这个值，就算类变动了，
	 * 它反序列化的时候也能和文件中的原值匹配上。
	 * 而新增的值则会设置成null，删除的值则不会显示。
	*/
	
	private static final long serialVersionUID = -2337937881709830078L;

	// private static final long serialVersionUID = 1L;

	private int id ;
	private String name;
	public AA(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public String toString() {
		return "DATA: " + id + " " + name;
	}


}