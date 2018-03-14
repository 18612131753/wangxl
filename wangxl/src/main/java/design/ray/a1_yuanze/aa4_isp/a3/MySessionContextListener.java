package design.ray.a1_yuanze.aa4_isp.a3;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * session创建和关闭，可以视为用户登录和退出
 * */
public class MySessionContextListener implements HttpSessionListener{

    private final static 
    org.apache.log4j.Logger logger = 
        org.apache.log4j.Logger.getLogger(MySessionContextListener.class);
    
    @Override
    public void sessionCreated(HttpSessionEvent httpsessionevent) {
        logger.info("session创建......." + httpsessionevent.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpsessionevent) {
        logger.info("session关闭......." + httpsessionevent.getSession().getId());
    }

}
