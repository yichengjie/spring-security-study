package com.yicj.web.config;

import java.util.List;
import java.util.ArrayList;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yicj.web.filter.TimeFilter;

@Configuration
public class WebConfig {

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
