package hadoop.ray.hadoop2.hive.udf;

import org.apache.hadoop.hive.ql.exec.UDAF;
import org.apache.hadoop.hive.ql.exec.UDAFEvaluator;
import org.apache.hadoop.io.DoubleWritable;

/**
 * 计算所有人员信息的平均年龄
 * */
public class MyUDAF extends UDAF {
	
	// 内部类
	public static class Evaluator implements UDAFEvaluator {
		
		public static class PartialResult {   
            double sum;   
            int count;   
        }   
		
		// 最终结果
		private PartialResult result;
		
		// 负责初始化计算函数并设置它的内部状态，
		// result是存放最终结果的
		@Override
		public void init() {
			result = null;
		}

		// 每次对一个新值进行聚集计算都会调用iterate方法
		public boolean iterate(Integer value) {
			if (value == null) {   
                return true;   
            }
			if (result == null) {   
				result = new PartialResult();   
            }
			result.sum += value.intValue();   
			result.count++; 
			return true;
		}

		// 合并两个部分聚集值会调用这个方法
		// 数据传输到reduce端前调用该函数，所以入仓必须和terminatePartial返回值相同 
		public boolean merge( PartialResult other ) {
			if (other == null) {   
                return true;   
            }   
            if (result == null) {   
            	result = new PartialResult();   
            }   
            result.sum += other.sum;   
            result.count+= other.count;   
            return true;   
		}

		// Hive需要部分聚集结果的时候会调用该方法
		// 会返回一个封装了聚集计算当前状态的对象
		public PartialResult terminatePartial() {
			return result ;   
		}
		
		// Hive需要最终聚集结果时候会调用该方法
		public DoubleWritable terminate() {
		  if (result == null) {   
			  return null ;   
		  }   
		  return new DoubleWritable(result.sum / result.count) ;   
		}
	}
}
