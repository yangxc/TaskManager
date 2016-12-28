package com.peraglobal.service;

import java.util.List;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.peraglobal.mapper.TaskMapper;
import com.peraglobal.model.Task;
import com.peraglobal.model.TaskConst;
import com.peraglobal.utils.CronExp;

/**
 *  <code>SchedulerService.java</code>
 *  <p>功能:框架任务调度器集成采集任务管理 Service
 *  
 *  <p>Copyright 安世亚太 2016 All right reserved.
 *  @author yongqian.liu	
 *  @version 1.0
 *  2016-12-6
 *  </br>最后修改人 无
 */
@Service("schedulerService")
public class SchedulerService {

	private static SchedulerFactory sf = new StdSchedulerFactory();
	private static Scheduler scheduler = null;
	
	@Autowired
    private TaskMapper taskMapper;
	
	@Autowired
	RestTemplate restTemplate;
	
	/**
     * 初始化任务调度器
     */
	public void initScheduler() {
		try {
			scheduler = sf.getScheduler();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	/**
     * 启动任务调度器
     */
	public void start() {
		try {
			scheduler.start();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		
	}

	/**
     * 暂停任务调度器
     */
	public void standby() {
		try {
			scheduler.standby();
        } catch (SchedulerException e) {
        	e.printStackTrace();
        }
	}
	
	/**
     * 停止任务调度器
     */
	public void shutdown() {
		try {
			scheduler.shutdown();
        } catch (SchedulerException e) {
        	e.printStackTrace();
        }
	}
	
	/**
     * 初始化所有状态为开始的任务
     */
	public void initJob() {
		try {
			// 查询所有已经启动的任务
			List<Task> tasks = taskMapper.getTaskByStart(TaskConst.STATE_STRAT);
			for (Task task : tasks) {
				this.addJob(task);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    /**
	 * 添加任务到调度器
	 * @param task 当前任务
	 */
	public void addJob(Task task) {
		try { 
			// 构建job信息
	        JobDetail jobDetail = JobBuilder
	        		.newJob(JobService.class)
	        		.withIdentity(task.getTaskId(), task.getGroupId())
	        		.storeDurably(true) // 指明任务就算没有绑定Trigger仍保留在Quartz的JobStore中
	        		.build();
	        
	        // 放入参数，运行时的方法可以获取
	        jobDetail.getJobDataMap().put(TaskConst.TASK_KEY, task);
	        	
			// 开始时间
			Trigger triggerStart = CronExp.triggerToOne(task.getTaskId(), task.getStartExpress(), TaskConst.TRIGGER_STRAT, jobDetail);
			scheduler.scheduleJob(jobDetail, triggerStart);

			// 结束时间
			Trigger triggerStop = CronExp.triggerToOne(task.getTaskId(), task.getStopExpress(), TaskConst.TRIGGER_STOP, jobDetail);
			scheduler.scheduleJob(triggerStop);
		} catch (Exception e) {
			e.printStackTrace();
		}   
	}
	
	/**
     * 从调度器中删除任务
     * @param task 当前任务
     */
	public void delJob(Task task) {
		try {
			JobKey jobKey = JobKey.jobKey(task.getTaskId(), task.getGroupId());
			scheduler.deleteJob(jobKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
     * 从调度器中删除触发器
     * @param task 当前任务
     */
    public void delTrigger(Task task){
    	try {
    		TriggerKey tk = TriggerKey.triggerKey(TaskConst.STATE_STRAT, task.getTaskId());
    		scheduler.resumeTrigger(tk);
    		tk = TriggerKey.triggerKey(TaskConst.STATE_STOP, task.getTaskId());
    		scheduler.resumeTrigger(tk);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
    }
    
    public void startJob(Task task) {
		// 执行命令
    	restTemplate.put(task.getStartCommand(), task.getTaskId());
    	
    	// 更新监控日志，后续完善
		
	}
    
	public void stopJob(Task task) {
		// 执行命令
    	restTemplate.put(task.getStopCommand(), task.getTaskId());
		
		// 更新监控日志，后续完善
	}
	
}
