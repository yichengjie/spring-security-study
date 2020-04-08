package com.yicj.security.browser;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailsService implements UserDetailsService {

	private Logger logger = LoggerFactory.getLogger(this.getClass()) ;
	
	@Autowired
	private PasswordEncoder passwordEncoder ;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("登录用户名： " + username);
		//根据用户名查询用户信息
		List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("admin");
		//根据查找到用户信息判断用户是否被冻结
		String password = passwordEncoder.encode("123456");
		logger.info("数据库密码是: " + password);
		boolean enabled = true ;
		boolean accountNonExpired = true ;
		boolean credentialsNonExpired = true ;
		boolean accountNonLocked = true ;
		User user = new User(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities) ;
		return user;
	}

}
