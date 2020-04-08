package com.yicj.security.browser;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder() ;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//super.configure(http);
		http.formLogin()
		.loginPage("/imooc-signin.html")
		.loginProcessingUrl("/authentication/form")
		//http.httpBasic()
		.and()
		.authorizeRequests() //对请求做一个授权
		.antMatchers("/imooc-signin.html").permitAll() //访问到这个页面时不需要身份认证
		.anyRequest() //任何请求
		.authenticated() //都需身份认证
		.and()
		.csrf().disable()
		;
		
	}

}
