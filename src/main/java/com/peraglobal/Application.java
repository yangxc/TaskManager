package com.peraglobal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.peraglobal.common.CurrentApplicationContext;

/**
 * <code>Application.java</code>
 * <p>
 * 功能:启动类
 * 
 * <p>
 * Copyright 安世亚太 2016 All right reserved.
 * 
 * @author yongqian.liu
 * @version 1.0 2016-12-2 </br>
 * 			最后修改人 无
 */
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		// 设置启动类
		ApplicationContext applicationContext = SpringApplication.run(Application.class, args);
		// 设置 spring 上下文到自定义类中
		CurrentApplicationContext.setApplicationContext(applicationContext);
	}

	/**
	 * 设置 RestTemplate 功能，在项目中调用 RESTFul API
	 * 
	 * @param builder
	 * @return
	 */
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
}
