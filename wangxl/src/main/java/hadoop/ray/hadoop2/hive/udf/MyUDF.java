package hadoop.ray.hadoop2.hive.udf;

import org.apache.hadoop.hive.ql.exec.UDF;

/**
 * 
 * */
public class MyUDF extends UDF {
	
	public String evaluate() {
		return "hello world!";
	}
 
	public String evaluate(String str) {
		return "hello world: " + str;
	}

}
