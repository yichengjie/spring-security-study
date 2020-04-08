package com.yicj.web.config;

import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.yicj.web.filter.TimeFilter;
import com.yicj.web.interceptor.TimeInterceptor;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

	@Autowired
	private TimeInterceptor timeInterceptor ;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(timeInterceptor) ;
	}
	
	//第三方filter的注册方式
	@Bean
	public FilterRegistrationBean timeFilter() {
		FilterRegistrationBean resiBean = new FilterRegistrationBean() ;
		TimeFilter timeFilter = new TimeFilter() ;
		resiBean.setFilter(timeFilter);
		List<String> urls = new ArrayList<>() ;
		urls.add("/*") ;
		resiBean.setUrlPatterns(urls);
		return resiBean ;
	}
}
