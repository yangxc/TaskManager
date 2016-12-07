package com.peraglobal.utils;

import org.quartz.CronScheduleBuilder;
import org.quartz.DailyTimeIntervalScheduleBuilder;
import org.quartz.JobDetail;
import org.quartz.TimeOfDay;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

import com.peraglobal.model.Task;

/**
 *  <code>CronExp.java</code>
 *  <p>功能:定时任务规则转换类
 *  
 *  <p>Copyright 安世亚太 2016 All right reserved.
 *  @author yongqian.liu	
 *  @version 1.0
 *  2016-12-7
 *  </br>最后修改人 无
 */
public class CronExp {

	/**
	 * 简单的触发器
	 * @param task 任务对象
	 * @return Trigger
	 */
	public static Trigger triggerToSimple(Task task){
        Trigger trigger = TriggerBuilder
        		.newTrigger()
        		.withIdentity(task.getTaskId(), task.getGroupId())
        		.build();
        return trigger;
	}

	/**
	 * 创建天定时触发器实现
	 * @param jobDetail 任务详细信息
	 * @param taskTrigger 定时任务对象
	 * @param TaskJob task 任务对象
	 * @param flgId 组合ID值
	 * @return 实现每天触发的
	 */
	public static Trigger triggerToOne(String taskId, String dateTime, String operation, JobDetail jobDetail) {
		/***************************
    	 * 功能：触发一次
    	 * 参数：秒 分 时 日 月 年
    	 * 示例：00 00 08 01 07 2015 触发时间在 2015-7-1 8:00:00
    	 ***************************/
		
		String time = dateTime.replaceAll("-", ":").replaceAll(" ", ":");
		String[] hms = time.split(":"); // 时 分 秒 转换字符串数组
		String cron = hms[5] + " " + hms[4] + " " + hms[3] + " " + hms[2] + " " + hms[1] + " ? " + hms[0];
		Trigger trigger = TriggerBuilder
				.newTrigger()
				.withIdentity(operation, taskId)
				.withSchedule(
						CronScheduleBuilder                                 
								.cronSchedule(cron)).forJob(jobDetail).build(); 
		return trigger;
	}
	
	/**
	 * 每天定时任务规则触发器
	 * @param job 任务对象
	 * @param jobDetail 在调度器中的任务详细对象
	 * @param taskTrigger 任务定时对象
	 * @param flgId 组合 ID：为实现开始和结束触发器生成使用
	 * @return 天规则的触发器对象
	 */
	public static Trigger triggerToDay(String taskId, String dateTime, String operation, JobDetail jobDetail) {
    	/***************************
    	 * 功能：按照天触发任务
    	 * 参数：秒 分 时
    	 * 参数：隔多少天
    	 ***************************/
		
		// 格式化时间字符串		
		int[] hms = formatHms(dateTime);
		TimeOfDay timeOfDay = new TimeOfDay(hms[0], hms[1], hms[2]);
		Trigger trigger = TriggerBuilder
				.newTrigger()
				.withIdentity(operation, taskId)
				.withSchedule(DailyTimeIntervalScheduleBuilder.dailyTimeIntervalSchedule()
						.startingDailyAt(timeOfDay) // 触发时间
						.withIntervalInHours(24) // 间隔  space * 24 = space * (1 * 24) 
						.withRepeatCount(-1) // 一直执行
						).forJob(jobDetail).build();
		return trigger;
	}
	
	/**
	 * 每周定时任务规则触发器
	 * @param job 任务对象
	 * @param jobDetail 在调度器中的任务详细对象
	 * @param taskTrigger 任务定时对象
	 * @param flgId 组合 ID：为实现开始和结束触发器生成使用
	 * @return 周规则的触发器对象
	 */
	public static Trigger triggerToWeek(String taskId, String cron, String operation, JobDetail jobDetail) {
		Trigger trigger = TriggerBuilder
				.newTrigger()
				.withIdentity(operation, taskId)
				.withSchedule(
						CronScheduleBuilder                                 
								.cronSchedule(cron)).forJob(jobDetail).build();
		return trigger;
	}
	
	/**
	 * 时分秒格式的字符串格式为 时分秒的数组
	 * @param time 当前时间
	 * @return {hh,mm,ss} 时间格式的数组
	 */
	private static int[] formatHms(String time){
		try {
			if(null == time)
				return null;
			String times[] = time.split(":");
			int hms[] = new int[times.length];
			for (int i = 0; i < times.length; i++) {
				hms[i] = Integer.parseInt(times[i]);
			}
			return hms;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
}
