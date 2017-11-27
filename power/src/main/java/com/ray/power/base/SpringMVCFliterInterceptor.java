package com.ray.power.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
/*
 * springMVC过滤器
 * */
public class SpringMVCFliterInterceptor extends HandlerInterceptorAdapter {

	@Override
	//实现预处理，
	public boolean preHandle(
		HttpServletRequest request,
		HttpServletResponse response, 
		Object handler 
	) throws Exception {
//		String url = request.getRequestURI();
//		java.util.Enumeration names = request.getParameterNames(); 
//		int i = 0; 
//		if (names != null) { 
//			while (names.hasMoreElements()) { 
//				String name = (String) names.nextElement(); 
//				if (i == 0) { 
//					url = url + "?"; 
//				} else { 
//					url = url + "&"; 
//				}
//				i++; 
//				String value = request.getParameter(name); 
//				if (value == null) { 
//					value = ""; 
//				}
//				url = url + name + "=" + value; 
//			}
//		}
//		System.out.println( "##访问连接为 "+url);
		return true;
	}
	
	@Override
	//后处理（调用了Service并返回ModelAndView，但未进行页面渲染），有机会修改ModelAndView
	public void postHandle(    
		HttpServletRequest request,
		HttpServletResponse response,
		Object handler,
		ModelAndView modelAndView
	) throws Exception {
		
    }
	
	@Override
	//返回处理（已经渲染了页面） 
    public void afterCompletion(    
		HttpServletRequest request, 
		HttpServletResponse response, 
		Object handler,
		Exception ex
	)throws Exception {
    	
    }   
}
