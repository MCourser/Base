package com.machao.base.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	private static final int TOKEN_VALIDITY_SECONDS = 1209600;
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private AjaxAuthSuccessHandler ajaxAuthSuccessHandler;
	
	@Autowired
	private AjaxAuthFailureHandler ajaxAuthFailureHandler;
	
	@Autowired
	private AjaxLogoutSuccessHandler ajaxLogoutSuccessHandler;
	
	@Autowired
	private MyAuthenticationEntryPoint myAuthenticationEntryPoint;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.
			csrf().disable().
			exceptionHandling().authenticationEntryPoint(myAuthenticationEntryPoint).
			and().
				authorizeRequests().
//				antMatchers("/user/me").permitAll().
				anyRequest().authenticated().
			and().
		    	addFilterAt(myUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class).
				formLogin().loginProcessingUrl("/user/login").
				successHandler(ajaxAuthSuccessHandler).
				failureHandler(ajaxAuthFailureHandler).
				usernameParameter("username").
				passwordParameter("password").
				permitAll().
			and().
				logout().logoutUrl("/user/logout").
				logoutSuccessHandler(ajaxLogoutSuccessHandler).
				permitAll().
				invalidateHttpSession(true).
			and().
				httpBasic().
			and().
		    	rememberMe().tokenValiditySeconds(TOKEN_VALIDITY_SECONDS);
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	    auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder);  
        auth.eraseCredentials(false);     
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public MyUsernamePasswordAuthenticationFilter myUsernamePasswordAuthenticationFilter() throws Exception {
		MyUsernamePasswordAuthenticationFilter filter = new MyUsernamePasswordAuthenticationFilter();
	    filter.setAuthenticationManager(authenticationManagerBean());
	    filter.setAuthenticationSuccessHandler(ajaxAuthSuccessHandler);
	    filter.setAuthenticationFailureHandler(ajaxAuthFailureHandler);
	    filter.setFilterProcessesUrl("/user/login");
	    return filter;
	}

}