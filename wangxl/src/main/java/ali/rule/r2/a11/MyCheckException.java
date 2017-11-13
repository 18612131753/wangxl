package ali.rule.r2.a11;


/**
 * 自定义一个check异常
 * */
public class MyCheckException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public MyCheckException(){       
	}

	public MyCheckException(String msg){
		super(msg);
	}

}
