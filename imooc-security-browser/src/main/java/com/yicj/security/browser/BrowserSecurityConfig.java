package com.yicj.security.browser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.yicj.security.browser.authentication.ImoocAuthenctiationFailureHandler;
import com.yicj.security.browser.authentication.ImoocAuthenticationSuccessHandler;
import com.yicj.security.core.properties.SecurityProperties;

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private SecurityProperties securityProperties ;
	@Autowired
	private ImoocAuthenticationSuccessHandler imoocAuthenticationSuccessHandler ;
	@Autowired
	private ImoocAuthenctiationFailureHandler imoocAuthenctiationFailureHandler ;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder() ;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//super.configure(http);
		http.formLogin()
		.loginPage("/authentication/require")
		.loginProcessingUrl("/authentication/form")
		.successHandler(imoocAuthenticationSuccessHandler)//登录成功处理器
		.failureHandler(imoocAuthenctiationFailureHandler)//失败处理器
		//http.httpBasic()
		.and()
		.authorizeRequests() //对请求做一个授权
		.antMatchers("/authentication/require",securityProperties.getBrowser().getLoginPage()).permitAll() //访问到这个页面时不需要身份认证
		.anyRequest() //任何请求
		.authenticated() //都需身份认证
		.and()
		.csrf().disable()
		;
		
	}

}
