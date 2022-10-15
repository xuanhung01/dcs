package com.shf.dcs.configuration;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

import com.shf.dcs.controller.AdminController;

@Configuration
@ComponentScan(basePackages = { "com.shf.dcs.configuration",
		"com.shf.dcs.service", "com.shf.dcs.model","com.shf.dcs.model.job","com.shf.dcs.client.rest" })
@PropertySource("classpath:config.properties")
public class SpringAppConfig {

	private static Logger logger = Logger.getLogger(SpringAppConfig.class);
	
	@Autowired
	private Environment env;

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyPlaceHolderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}