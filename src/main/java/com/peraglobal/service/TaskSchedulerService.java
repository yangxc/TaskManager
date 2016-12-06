package com.peraglobal.service;

import java.util.List;

import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.scheduling.concurrent.ScheduledExecutorFactoryBean;

import com.peraglobal.mapper.TaskMapper;
import com.peraglobal.model.Task;

/**
 *  <code>TaskSchedulerService.java</code>
 *  <p>功能:框架任务调度器集成采集任务管理 Service
 *  
 *  <p>Copyright 安世亚太 2016 All right reserved.
 *  @author yongqian.liu	
 *  @version 1.0 
 *  </br>最后修改人 无
 */
@Service("taskSchedulerService")
public class TaskSchedulerService {

	
	@Autowired
    private TaskMapper taskMapper;
	
	public void initTask() {
		List<Task> tasks = taskMapper.getTaskAll();
		
	}

	public void start() {
		// TODO Auto-generated method stub
		
	}

	

	
	
	
	
}
