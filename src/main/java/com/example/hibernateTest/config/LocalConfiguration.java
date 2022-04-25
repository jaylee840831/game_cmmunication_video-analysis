package com.example.hibernateTest.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.server.i18n.AcceptHeaderLocaleContextResolver;

@Configuration
public class LocalConfiguration {

	@Bean
	public AcceptHeaderLocaleContextResolver localeContextResolver() {
		return new AcceptHeaderLocaleContextResolver();
	}
	
	@Bean
	public ResourceBundleMessageSource messageSource() {
		final ResourceBundleMessageSource messageSource=new ResourceBundleMessageSource();
		
		messageSource.setBasename("locale/message/message");

		return messageSource;
	}
}
