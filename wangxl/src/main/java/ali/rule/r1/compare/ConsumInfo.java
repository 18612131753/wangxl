package ali.rule.r1.compare;

import java.util.Date;

/**
 * 因为要实现对ConsumInfo对象的排序，所以在ConsunInfo类中要实现Comparable接口，也就是要实现compareTo()方法
 * 具体的比较参照：依次按照price、uid进行倒序排序
 * 
 * @author breeze
 *
 */
public class ConsumInfo implements Comparable<ConsumInfo> {
	
	private int uid;
	private String name;
	private double price;
	private Date datetime;

	public ConsumInfo() {
		// TODO Auto-generated constructor stub
	}

	public ConsumInfo(int uid, String name, double price, Date datetime) {
		this.uid = uid;
		this.name = name;
		this.price = price;
		this.datetime = datetime;

	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	@Override
	public String toString() {
		return "ConsumInfo [uid=" + uid + ", name=" + name + ", price=" + price + ", datetime=" + datetime + "]";
	}

	/**
	 * 这里比较的是什么, Collections.sort方法实现的就是按照此比较的东西排列 顺序（从小到大）： if(price <
	 * o.price){ return -1; } if(price > o.price){ return 1; } 倒序（从大到小）：
	 * if(price < o.price){ return 1; } if(price > o.price){ return -1; }
	 * 
	 */
	@Override
	public int compareTo(ConsumInfo o) {
		// 首先比较price，如果price相同，则比较uid
		if (price < o.price) {
			return -1;
		}
		if (price > o.price) {
			return 1;
		}

		if (price == o.price) {
			if (uid < o.uid) {
				return -1;
			}
			if (uid > o.uid) {
				return 1;
			}
		}
		return 0;
	}

}
