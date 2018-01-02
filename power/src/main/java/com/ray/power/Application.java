package com.ray.power;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;

//自动扫描注入配置的包路径下的bean 
@SpringBootApplication(scanBasePackages={"com.ray.power"})
//这个就是扫描相应的Servlet包;
@ServletComponentScan
public class Application extends SpringBootServletInitializer {  

	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		// 容器启动，自动加载
        return application.sources(Application.class);
    }
	 
    public static void main(String[] args) {  
    	/**
    	 * 该方法返回一个ApplicationContext对象,
    	 * 使用注解的时候返回的具体类型是AnnotationConfigApplicationContext或AnnotationConfigEmbeddedWebApplicationContext，
    	 * 当支持web的时候是第二个。
    	 * */
        SpringApplication.run(Application.class, args);
    }

}
