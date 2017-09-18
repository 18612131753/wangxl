package ali.rule.r1.compare;

import java.util.Comparator;
import java.util.Random;

public class ComparatorConsunInfoError implements Comparator<ConsumInfo> {

	// 下例中没有处理相等的情况，实际使用中可能会出现异常
	@Override
	public int compare(ConsumInfo o1, ConsumInfo o2) {
        Random random = new Random();
        int s = random.nextInt();
		return s ;
	}

}