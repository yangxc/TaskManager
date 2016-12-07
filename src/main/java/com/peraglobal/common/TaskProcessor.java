package com.peraglobal.common;

import javax.annotation.Resource;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import com.peraglobal.service.SchedulerService;

/**
 *  <code>TaskProcessor.java</code>
 *  <p>功能:重写 spring 监听器在服务启动或者新增加一个线程时会触发
 *  <p>此类主要用于容器启动注入所有bean后初始化任务调度器并加载所有任务
 *  
 *  <p>Copyright 安世亚太 2016 All right reserved.
 *  @author yongqian.liu	
 *  @version 1.0
 *  @see 2016-12-6
 *  </br>最后修改人 无
 */
@Configuration
public class TaskProcessor implements ApplicationListener<ContextRefreshedEvent> {

	@Resource
    private SchedulerService taskSchedulerService;
	
	// 是否初始化
	private static boolean isStart = true;
	 
	/**
	 * 工程启动时注入所有任务，此方法会重复调用多次，
	 * 使用静态变量控制任务只在系统启动时，触发加载任务工作
	 */
	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		// 系统启动时执行
		if (isStart) { 
			isStart = false;
			taskSchedulerService.initScheduler();
			taskSchedulerService.start(); // 启动调度器
			taskSchedulerService.initJob(); // 初始化任务
		}
	}
}
