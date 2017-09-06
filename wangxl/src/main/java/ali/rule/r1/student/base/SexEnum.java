package ali.rule.r1.student.base;

public enum SexEnum {

	BOY("男", 1), GRIL("女", 0);

	private String name;
	private int value;

	// 构造方法
	private SexEnum(String name, int value) {
		this.name = name;
		this.value = value;
	}

	// 普通方法
	public static String getName(int value) {
		for (SexEnum s : SexEnum.values()) {
			if (s.getValue() == value) {
				return s.name;
			}
		}
		return null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
