package com.peraglobal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peraglobal.mapper.TaskMapper;
import com.peraglobal.model.Task;
import com.peraglobal.model.TaskConst;
import com.peraglobal.utils.Result;

/**
 *  <code>TaskService.java</code>
 *  <p>功能:任务功能 Service
 *  
 *  <p>Copyright 安世亚太 2016 All right reserved.
 *  @author yongqian.liu	
 *  @version 1.0
 *  2016-12-5
 *  </br>最后修改人 无
 */
@Service
public class TaskService {

	@Autowired
    private SchedulerService schedulerService;
	
	@Autowired
    private TaskMapper taskMapper;
	
	
	public List<Task> getTaskList(String groupId) {
		return taskMapper.getTaskList(groupId);
	}
	
	public Task getTask(String taskId) {
		return taskMapper.getTask(taskId);
	}

	public int createTask(Task task) {
		Task t = taskMapper.getTaskByTaskName(task.getTaskName(), task.getGroupId());
		if(t != null) {
			return Result.EXISTS;
		}
		try {
			taskMapper.createTask(task);
		} catch (Exception e) {
			return Result.ERROR;
		}
		return Result.SUCCESS;
	}

	public int removeTask(String taskId) {
		Task task = taskMapper.getTask(taskId);
		schedulerService.delTrigger(task); // 删除触发器
		schedulerService.delJob(task);
		try {
			taskMapper.removeTask(taskId);
			return Result.SUCCESS;
		} catch (Exception e) {
			return Result.ERROR;
		}
	}

	public int editTask(Task task) {
		try {
			schedulerService.delTrigger(task);
			schedulerService.delJob(task);
			taskMapper.editTask(task);
		} catch (Exception e) {
			return Result.ERROR;
		}
		return Result.SUCCESS;
	}

	public int renameTask(String taskId, String taskName) {
		try {
			taskMapper.renameTask(taskId, taskName);
		} catch (Exception e) {
			return Result.ERROR;
		}
		return Result.SUCCESS;
	}

	public int start(String taskId) {
		Task task = taskMapper.getTask(taskId);
		try {
			schedulerService.addJob(task);
			taskMapper.updateStateByTaskId(TaskConst.STATE_STRAT, taskId);
		} catch (Exception e) {
			return Result.ERROR;
		}
		return Result.SUCCESS;
	}

	public int stop(String taskId) {
		Task task = taskMapper.getTask(taskId);
		try {
			schedulerService.delTrigger(task);
			schedulerService.delJob(task);
			// 执行停止采集命令，预留
			
			taskMapper.updateStateByTaskId(TaskConst.STATE_STOP, taskId);
		} catch (Exception e) {
			return Result.ERROR;
		}
		return Result.SUCCESS;
	}
}
