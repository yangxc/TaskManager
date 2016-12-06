package com.peraglobal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peraglobal.mapper.TaskMapper;
import com.peraglobal.model.Task;

@Service
public class TaskService {

	@Autowired
    private TaskMapper taskMapper;
	
	
	public List<Task> getTaskList(String groupId) {
		return taskMapper.getTaskList(groupId);
	}
	
	public Task getTask(String taskId) {
		return taskMapper.getTask(taskId);
	}

	public int createTask(Task task) {
		return taskMapper.createTask(task);
	}

	public int removeTask(String taskId) {
		return taskMapper.removeTask(taskId);
	}

	public int editTask(String taskId) {
		return taskMapper.editTask(taskId);
	}

	public int renameTask(String taskId, String taskName) {
		return taskMapper.renameTask(taskId, taskName);
	}

	public int start(String taskId) {
		return taskMapper.start(taskId);
	}

	public void reset(String taskId) {
		this.stop(taskId);
		this.start(taskId);
	}

	public int stop(String taskId) {
		return taskMapper.stop(taskId);
	}
}
