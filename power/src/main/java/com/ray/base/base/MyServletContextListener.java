package com.ray.base.base;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 容器启动，关闭监听
 * 需要web.xml中配置此监听
 * */
public class MyServletContextListener implements ServletContextListener{

	private Logger logger = LoggerFactory.getLogger(MyServletContextListener.class);
	
	/**
	 * 容器关闭时被调用
	 * */
	public void contextDestroyed(ServletContextEvent arg0) {
		logger.info("容器关闭.......");
	}

	/**容器服务器启动时被调用
	 * */
	public void contextInitialized(ServletContextEvent arg0) {
		logger.info("容器启动.......");
	//	logger.info("正在装载数据字典.......");
	//	DicUtil.getInstance().getNormalDic().reload();
		logger.info("装载数据字典.......结束！");
	}

}
