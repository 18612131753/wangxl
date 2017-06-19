package hadoop.ray.hadoop2.hive.udf;

import org.apache.hadoop.hive.ql.exec.UDAF;
import org.apache.hadoop.hive.ql.exec.UDAFEvaluator;

/**
 * SUM运算
 * UDAF是输入多个数据行，产生一个数据行 
 * 用户自定义UDAF必须继承UDFA，必须提供一个实现了UDAFEvaluator接口的内部类 
 * */
public class MyUDAFSum extends UDAF {
	
	public static class MaxiNumberIntUDAFEvaluator implements UDAFEvaluator {
		// 最终结果
		private int result;
		
		// 负责初始化计算函数并设置它的内部状态，
		// result是存放最终结果的
		@Override
		public void init() {
			result = 0;
		}

		// 每次对一个新值进行聚集计算都会调用iterate方法
		public boolean iterate(int value) {
			result += value ;
			return true;
		}

		// 合并两个部分聚集值会调用这个方法
		public boolean merge(int other) {
			return iterate(other);
		}

		// Hive需要部分聚集结果的时候会调用该方法
		// 会返回一个封装了聚集计算当前状态的对象
		public int terminatePartial() {
			return result;
		}
		
		// Hive需要最终聚集结果时候会调用该方法
		public int terminate() {
			return result ;
		}
	}
}
