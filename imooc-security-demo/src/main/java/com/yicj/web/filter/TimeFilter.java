package com.yicj.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


//@Component
public class TimeFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("time filter start ...");
		long start = System.currentTimeMillis() ;
		//后续的处理
		chain.doFilter(request, response);
		long end = System.currentTimeMillis() ;
		System.out.println("time filter 耗时：" + (end -start));
		System.out.println("time filter end finish ...");
	}

	@Override
	public void destroy() {
		
	}

}
