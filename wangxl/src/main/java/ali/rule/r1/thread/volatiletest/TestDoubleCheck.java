package ali.rule.r1.thread.volatiletest;

/**
 * 优化后的单例模式
 * */
public class TestDoubleCheck {

	private volatile Integer sing_type ;
	
	public Integer getSing_type(){  
	  if (sing_type == null){   
	    synchronized(this){   
	      if (sing_type==null){  
	    	  sing_type = new Integer(0);    
	      }     
	    }    
	  }  
	  return sing_type;  
	} 
}