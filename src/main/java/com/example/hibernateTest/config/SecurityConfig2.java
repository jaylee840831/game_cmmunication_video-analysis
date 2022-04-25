package com.example.hibernateTest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//@Configuration
//public class SecurityConfig2 extends WebSecurityConfigurerAdapter{
//
//	/*
//	 * 基於配置類來創建帳號密碼
//	 * */
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
//		//密碼加密
//		String password =passwordEncoder.encode("123");
//		auth.inMemoryAuthentication().withUser("jay").password(password).roles("admin");
//	}
//	
//	@Bean
//	PasswordEncoder password() {
//		return new BCryptPasswordEncoder();
//	}
//}
