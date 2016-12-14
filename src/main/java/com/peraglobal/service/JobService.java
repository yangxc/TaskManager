package com.peraglobal.service;

import java.util.Date;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import com.peraglobal.common.ApplicationContextUtil;
import com.peraglobal.mapper.TaskMapper;
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
	
	@Autowired
    private TaskMapper taskMapper;
	
	/**
	 * 任务触发器出发入口，任务定时出发执行功能
	 */
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		taskMapper =  (TaskMapper)ApplicationContextUtil.getBean("taskMapper");
		
		// 获得当前执行任务对象
		Task task = (Task)context.getJobDetail().getJobDataMap().get(TaskConst.TASK_KEY);
		
		// 获得当前触发器名称标识
		String triggerKey = context.getTrigger().getKey().toString();
		String operation = triggerKey.substring(triggerKey.indexOf(".") + 1, triggerKey.length());
		
		Task currentTask = taskMapper.getTask(task.getTaskId());
		if(currentTask != null) {
			// 匹配标识为开始触发器，则执行开始任务
			if(operation.equals(TaskConst.TRIGGER_STRAT)) {
				// 判断当前任务是否非开始状态
				if(!currentTask.getTaskState().equals(TaskConst.STATE_STRAT)) {
					
					// 执行命令
					task.getStartCommand();
					System.out.println("开始操作功能！");
					// 更新任务状态
					task.setTaskState(TaskConst.STATE_STRAT);
					task.setUpdateTime(new Date());
					taskMapper.updateStateByTask(task);
					
					// 更新监控日志，后续完善
				}
			}else {
				// 判断当前任务是否是开始状态
				if(currentTask.getTaskState().equals(TaskConst.STATE_STRAT)) {
					
					// 执行命令
					task.getStopCommand();
					System.out.println("停止操作功能！");
					// 更新任务状态
					task.setTaskState(TaskConst.STATE_STOP);
					task.setUpdateTime(new Date());
					taskMapper.updateStateByTask(task);
					
					// 更新监控日志，后续完善
				}
			}
		}
    }
}
