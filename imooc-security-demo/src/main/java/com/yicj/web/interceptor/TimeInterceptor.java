package com.yicj.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class TimeInterceptor implements HandlerInterceptor {
	
	@Override//handler 处理请求的方法的信息
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("preHandle");
		HandlerMethod handlerMethod = (HandlerMethod)handler ;
		System.out.println("bean class name :" + handlerMethod.getBean().getClass().getName());
		System.out.println("bean method name :" + handlerMethod.getMethod().getName());
		request.setAttribute("startTime", System.currentTimeMillis());
		//返回true：继续执行后面的方法，false不执行后面的逻辑
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("postHandle");
		Long start = (Long)request.getAttribute("startTime") ;
		Long end = System.currentTimeMillis();
		System.out.println("time interceptor 耗时：" + (end -start));
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("afterCompletion");
		Long start = (Long)request.getAttribute("startTime") ;
		Long end = System.currentTimeMillis();
		System.out.println("time interceptor 耗时：" + (end -start));
		System.out.println("ex is : " +ex);
	}

}
