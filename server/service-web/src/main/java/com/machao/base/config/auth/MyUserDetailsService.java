package com.machao.base.config.auth;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.machao.base.exception.UserNotFoundException;
import com.machao.base.model.User;
import com.machao.base.service.UserService;

@Component
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if (StringUtils.isBlank(username)) {
			throw new UsernameNotFoundException("username is empty");
		}

		User user = userService.selectByName(username);
		if(user == null) throw new UserNotFoundException();
		
		Set<GrantedAuthority> authorities = new HashSet<>();
		Optional.of(user.getRoles()).ifPresent(roles->{
			roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
		});
		

		return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), 
				true, // 是否可用
				true, // 是否过期
				true, // 证书不过期为true
				true, // 账户未锁定为true
				authorities);
	}

}
