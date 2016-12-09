package com.peraglobal.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peraglobal.mapper.TaskMapper;
import com.peraglobal.model.Task;
import com.peraglobal.model.TaskConst;

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
	
	/**
	 * 根据组 ID 查询任务列表
	 * @param groupId 组 ID
	 * @return List<Task> 任务列表
	 * @throws Exception
	 */
	public List<Task> getTaskList(String groupId) throws Exception {
		return taskMapper.getTaskList(groupId);
	}
	
	/**
	 * 根据任务 ID 查询任务对象
	 * @param taskId 任务 ID
	 * @return Task 任务对象
	 * @throws Exception
	 */
	public Task getTask(String taskId) throws Exception {
		return taskMapper.getTask(taskId);
	}

	/**
	 * 创建任务
	 * @param task 任务对象
	 * @return taskId 任务 ID
	 * @throws Exception
	 */
	public String createTask(Task task) throws Exception {
		// 根据当前任务名称和组 ID 查询任务是否存在，则不创建
		Task t = taskMapper.getTaskByTaskName(task);
		if(t == null) {
			// uuid 任务 ID
			task.setTaskId(java.util.UUID.randomUUID().toString());
			// 默认状态为：就绪
			task.setTaskState(TaskConst.STATE_READY);
			task.setCreateTime(new Date());
			task.setUpdateTime(new Date());
			taskMapper.createTask(task);
			return task.getTaskId();
		}
		return null;
	}

	/**
	 * 通过任务 ID删除对象
	 * @param taskId 任务 ID
	 * @throws Exception
	 */
	public void removeTask(String taskId) throws Exception {
		// 通过任务 ID 查询任务对象是否存在
		Task t = taskMapper.getTask(taskId);
		if(t != null) {
			// 判断任务对象是否在任务调度器中，如果状态为：非就绪，则存在任务调度器中
			if(!t.getTaskState().equals(TaskConst.STATE_READY)) {
				schedulerService.delTrigger(t);
				schedulerService.delJob(t);
			}
			taskMapper.removeTask(taskId);
		}
	}

	/**
	 * 编辑任务对象
	 * @param task 任务对象
	 * @throws Exception
	 */
	public void editTask(Task task) throws Exception {
		// 查询任务对象是否存在
		Task t = taskMapper.getTask(task.getTaskId());
		if(t != null) {
			// 判断任务对象是否在任务调度器中，如果状态为：非就绪，则存在任务调度器中
			if(!t.getTaskState().equals(TaskConst.STATE_READY)) {
				schedulerService.delTrigger(task);
				schedulerService.delJob(task);
			}
			task.setUpdateTime(new Date());
			taskMapper.editTask(task);
		}
	}

	/**
	 * 开始任务
	 * @param taskId 任务 ID
	 * @throws Exception
	 */
	public void start(String taskId) throws Exception {
		Task t = taskMapper.getTask(taskId);
		// 任务状态为：非开始，则开始任务
		if(t != null && !t.getTaskState().equals(TaskConst.STATE_STRAT)) {
			// 更新任务状态
			t.setTaskState(TaskConst.STATE_STRAT);
			t.setUpdateTime(new Date());
			taskMapper.updateStateByTask(t);
			// 添加到任务调度器
			schedulerService.addJob(t);
		}
	}

	/**
	 * 停止任务
	 * @param taskId
	 * @throws Exception
	 */
	public void stop(String taskId) throws Exception {
		Task t = taskMapper.getTask(taskId);
		// 任务状态为：开始，则停止任务
		if(t != null && t.getTaskState().equals(TaskConst.STATE_STRAT)) {
			// 更新任务状态为停止
			t.setTaskState(TaskConst.STATE_STOP);
			t.setUpdateTime(new Date());
			taskMapper.updateStateByTask(t);
			// 停止任务，手动调用停止任务操作
			schedulerService.stopJob(t);
			// 删除调度器中触发器
			schedulerService.delTrigger(t);
			// 删除调度器中任务
			schedulerService.delJob(t);
		}
	}
	
}
