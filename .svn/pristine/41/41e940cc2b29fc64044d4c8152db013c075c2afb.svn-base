package com.evmtv.epg.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 
 * @author fangzhu(fangzhu@evmtv.com)
 * @time 2013-5-31 下午2:53:01
 */
public class CheckLoginFilter implements Filter {

	private static String sessionName;
	private static  String timeOutUrl;
	
	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if(((HttpServletRequest)request).getSession().getAttribute(sessionName) == null){
			String url = ((HttpServletRequest)request).getRequestURL().toString();
			((HttpServletRequest)request).getSession().setAttribute(timeOutUrl, url);
			((HttpServletResponse)response).sendRedirect(((HttpServletRequest)request).getContextPath()+timeOutUrl);
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig arg0) throws ServletException {
		sessionName = arg0.getInitParameter("sessionName");
		timeOutUrl = arg0.getInitParameter("timeOutUrl");
	}

	
}
