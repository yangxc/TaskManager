package com.peraglobal.service;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.peraglobal.model.Task;
import com.peraglobal.model.TaskConst;

/**
 *  <code>JobService.java</code>
 *  <p>功能：任务调度器程序人口
 *  
 *  <p>Copyright 安世亚太 2016 All right reserved.
 *  @author yongqian.liu	
 *  @version 1.0
 *  @see 2016-12-7
 *  </br>最后修改人 无
 */
@DisallowConcurrentExecution
public class JobService implements Job {
	
	/**
	 * 所有任务触发操作执行入口
	 */
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		Task task = (Task)context.getJobDetail().getJobDataMap().get(TaskConst.TASK_KEY);
		// 标记
		String operation = context.getTrigger().getCalendarName();
		if(operation.equals(TaskConst.TRIGGER_STRAT)) {
			task.getStartCommand();
			System.out.println("开始操作功能！");
		}else {
			task.getStopCommand();
			System.out.println("停止操作功能！");
		}
    	
    }
}
