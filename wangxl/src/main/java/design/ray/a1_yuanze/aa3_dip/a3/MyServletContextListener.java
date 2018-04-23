package design.ray.a1_yuanze.aa3_dip.a3;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 容器启动，关闭监听
 * 需要web.xml中配置此监听
 * <listener>
        <listener-class>com.lenovo.lls.base.MyServletContextListener</listener-class>
    </listener>
    如果 ServletContextListener 中多定义了几种方法，比如session的创建、session的失效，则会变得很复杂
    public interface HttpSessionListener extends EventListener
    而同样，为了符合依赖倒置原则，监听器都继承了EventListener
 * */
public class MyServletContextListener implements ServletContextListener{

	private final static 
		org.apache.log4j.Logger logger = 
			org.apache.log4j.Logger.getLogger(MyServletContextListener.class);
	
	/**
	 * 容器关闭时被调用
	 * */
	public void contextDestroyed(ServletContextEvent arg0) {
		logger.info("servlet关闭.......");
		
	}

	/**容器服务器启动时被调用
	 * */
	public void contextInitialized(ServletContextEvent arg0) {
		logger.info("servlet启动.......");
	}
}
