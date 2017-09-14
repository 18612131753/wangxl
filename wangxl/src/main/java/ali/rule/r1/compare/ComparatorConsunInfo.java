package ali.rule.r1.compare;

import java.util.Comparator;

public class ComparatorConsunInfo implements Comparator<ConsumInfo> {

	private String order; // 指定排序顺序

	public ComparatorConsunInfo(String order) {
		this.order = order;
	}

	@Override
	public int compare(ConsumInfo o1, ConsumInfo o2) {
		int dayu = 1 ;
		int xiaoyu = -1;
		
		if("DESC".equals(this.order)){
			dayu = -1 ;
			xiaoyu = 1 ;
		}
		// 首先比较price，如果price相同，则比较uid
		if (o1.getPrice() > o2.getPrice()) {
			return dayu;
		}

		if (o1.getPrice() < o2.getPrice()) {
			return xiaoyu;
		}

		if (o1.getPrice() == o2.getPrice()) {
			if (o1.getUid() > o2.getUid()) {
				return dayu;
			}
			if (o1.getUid() < o2.getUid()) {
				return xiaoyu ;
			}
		}
		return 0;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}
	
}