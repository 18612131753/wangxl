package com.ray.power.login.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.ray.power.login.model.UserSession;
import com.ray.power.util.SessionUtil;

// @WebFilter(filterName = "loginFilter", urlPatterns = "/*")
public class LoginFilter implements Filter {
	
	private String[] includeUrls;
	
	private String[] excludeUrls;
	
	private String defaultLoginPage;

	public void destroy() {
		
	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse) resp;
		if (hasInIncludes(request)) {
			if (hasInExcludes(request) == false) {
				if (hasLogin(request)) {
					chain.doFilter(req, resp);
				} else {
					forwardDefaultPage(request, response);
				}
			} else {
				chain.doFilter(req, resp);
			}
		} else {
			chain.doFilter(req, resp);
		}
	}
	
	public boolean hasLogin(HttpServletRequest request) {
		UserSession userSession = SessionUtil.getUserSession(request.getSession());
		if (userSession != null) {
			return true;
		}
		return false;
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		String includeUrls = filterConfig.getInitParameter("includeUrls");
		this.includeUrls = toArray(includeUrls);
		String excludeUrls = filterConfig.getInitParameter("excludeUrls");
		this.excludeUrls = toArray(excludeUrls);
		this.defaultLoginPage = filterConfig.getInitParameter("defaultLoginPage");
	}
	
	private boolean hasInIncludes(HttpServletRequest request) {
		String url = getRequestUrl(request);
		if (includeUrls != null) {
			for (String str: includeUrls) {
				if (url.matches(str)) {
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean hasInExcludes(HttpServletRequest request) {
		String url = getRequestUrl(request);
		if (excludeUrls != null) {
			for (String str: excludeUrls) {
				if (url.matches(str)) {
					return true;
				}
			}
		}
		return false;
	} 
	
	private String getRequestUrl(HttpServletRequest request) {
		String 	contextPath = request.getContextPath(),
				requestUri = request.getRequestURI(),
				requestUrl = StringUtils.replace(requestUri, contextPath, "", 1);
		return requestUrl;
	}
	
	private void forwardDefaultPage(HttpServletRequest request, HttpServletResponse response) {
		String contextPath = request.getContextPath();
		try {
			response.sendRedirect(contextPath + defaultLoginPage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String[] toArray(String str) {
		if (str == null) {
			return null;
		}
		String[] temp = str.split(",");
		String[] ary = new String[temp.length];
		int i = 0;
		for (String s: temp) {
			s = s.replace("\n", " ");
			ary[i++] = s.trim();
		}
		return ary;
	}

}
