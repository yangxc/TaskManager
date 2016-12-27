package com.peraglobal.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peraglobal.common.IDGenerate;
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
	 * 根据页数查询任务列表
	 * @param pageNo 页数
	 * @return
	 * @throws Exception
	 */
	public List<Task> getTasks(int pageNo)  throws Exception {
		int start = (pageNo - 1) * 50;
		int end = start + 50 - 1;
		List<Task> tasks = taskMapper.getTasks();
		if (tasks != null && tasks.size() > 0) {
			List<Task> reTasks = new ArrayList<Task>();
			for (int i = 0; i < tasks.size(); i++) {
				if (i >= start && i <= end) {
					reTasks.add(tasks.get(i)); 
				}
			}
			return reTasks;
		}
		return null;
	}
	
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
		Task temp = taskMapper.getTaskByTaskName(task);
		if(temp == null) {
			task.setTaskId(IDGenerate.uuid()); // uuid 任务 ID
			task.setTaskState(TaskConst.STATE_READY); // 默认状态为：就绪
			task.setCreateTime(new Date());
			task.setUpdateTime(new Date());
			taskMapper.createTask(task);
			
			// 更新监控日志，后续完善
			
			// 添加到任务调度器
			schedulerService.addJob(task);
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
		Task task = taskMapper.getTask(taskId);
		if(task != null) {
			// 停止任务
			schedulerService.stopJob(task);
			
			// 在任务调度器中删除触发器
			if(!task.getTaskState().equals(TaskConst.STATE_STOP)) {
				schedulerService.delTrigger(task);
				schedulerService.delJob(task);
			}
			taskMapper.removeTask(taskId);
			
			// 删除监控日志，后续完善
		}
	}

	/**
	 * 编辑任务对象
	 * @param task 任务对象
	 * @throws Exception
	 */
	public void editTask(Task task) throws Exception {

		Task temp = taskMapper.getTask(task.getTaskId());
		if(temp != null) {
			// 删除任务触发器
			schedulerService.delTrigger(task);
			schedulerService.delJob(task);
			
			// 重新添加到任务调度器中
			schedulerService.addJob(task);
			
			// 更新任务
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
		Task task = taskMapper.getTask(taskId);
		// 任务状态为：非开始，则开始任务
		if(task != null && !task.getTaskState().equals(TaskConst.STATE_STRAT)) {
			// 手动开始任务
			schedulerService.startJob(task);
			
			// 更新任务状态
			task.setTaskState(TaskConst.STATE_STRAT);
			task.setUpdateTime(new Date());
			taskMapper.updateStateByTask(task);
			
			// 更新监控日志，后续完善
		}
	}

	/**
	 * 停止任务
	 * @param taskId
	 * @throws Exception
	 */
	public void stop(String taskId) throws Exception {
		Task task = taskMapper.getTask(taskId);
		// 任务状态为：开始，则停止任务
		if(task != null && task.getTaskState().equals(TaskConst.STATE_STRAT)) {
			
			// 停止任务，手动调用停止任务操作
			schedulerService.stopJob(task);
			
			// 删除调度器中触发器
			schedulerService.delTrigger(task);
			
			// 删除调度器中任务
			schedulerService.delJob(task);
			
			// 更新任务状态为停止
			task.setTaskState(TaskConst.STATE_STOP);
			task.setUpdateTime(new Date());
			taskMapper.updateStateByTask(task);
			
			// 更新监控日志，后续完善
		}
	}

	public List<Task> getTasksByState(String state) throws Exception {
		if (state.equals("READY")) {
			state = TaskConst.STATE_READY;
		} else if (state.equals("RUNNING")){
			state = TaskConst.STATE_STRAT;
		}else {
			state = TaskConst.STATE_STOP;
		}
		return taskMapper.getTasksByState(state);
	}

	
}
