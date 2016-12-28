package com.peraglobal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.peraglobal.model.Task;
import com.peraglobal.service.TaskService;

/**
 *  <code>TaskController.java</code>
 *  <p>功能:任务调度器 Controller
 *  
 *  <p>Copyright 安世亚太 2016 All right reserved.
 *  @author yongqian.liu	
 *  @version 1.0
 *  @see 2016-12-5
 *  </br>最后修改人 无
 */
@RestController
@RequestMapping("taskmanager/task")
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	
	/**
	 * 获得任务列表
	 * @param pageNo 页数
	 * @return List<Task> 任务列表
	 * @since 1.0
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/getTasks/{pageNo}", method = RequestMethod.GET)
	public ResponseEntity<List<Task>> getTasks(@PathVariable("pageNo") int pageNo) {
		try {
			List<Task> tasks = taskService.getTasks(pageNo);
			return new ResponseEntity<>(HttpStatus.OK).accepted().body(tasks);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	/**
	 * 获得任务列表
	 * @param groupId 组Id （多用户区分不同用户）
	 * @return List<Task> 任务列表
	 * @since 1.0
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/getTaskList/{groupId}", method = RequestMethod.GET)
	public ResponseEntity<List<Task>> getTaskList(@PathVariable("groupId") String groupId) {
		try {
			List<Task> tasks = taskService.getTaskList(groupId);
			return new ResponseEntity<>(HttpStatus.OK).accepted().body(tasks);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	/**
	 * 获得任务
	 * @param taskId 任务 ID
	 * @return Task 任务
	 * @since 1.0
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/getTask/{taskId}", method = RequestMethod.GET)
	public ResponseEntity<Task> getTask(@PathVariable("taskId") String taskId) {
		try {
			Task task = taskService.getTask(taskId);
			return new ResponseEntity<>(HttpStatus.OK).accepted().body(task);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	/**
	 * 创建任务
	 * @param task 任务对象
	 * @return taskId 创建成功返回任务 ID
	 * @since 1.0
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/createTask", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createTask(@RequestBody Task task) {
		try {
			String taskId = taskService.createTask(task);
			if(taskId != null) {
				return new ResponseEntity<>(HttpStatus.CREATED).accepted().body(taskId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
	}
	
	/**
	 * 移除任务
	 * @param taskId 任务 ID
	 * @return 状态码
	 * @since 1.0
	 */
	@RequestMapping(value = "/removeTask/{taskId}", method = RequestMethod.DELETE)
	public ResponseEntity<?> removeTask(@PathVariable("taskId") String taskId) {
		try {
			taskService.removeTask(taskId);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {}
		return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
	}
	
	/**
	 * 编辑任务
	 * @param task 任务对象
	 * @return 状态码
	 * @since 1.0
	 */
	@RequestMapping(value = "/editTask", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> editTask(@RequestBody Task task) {
		try {
			taskService.editTask(task);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {}
		return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
	}
	
	/**
	 * 开始任务
	 * @param taskId 任务 ID
	 * @return 状态码
	 * @since 1.0
	 */
	@RequestMapping(value = "/start", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> start(@RequestBody Task task) {
		try {
			taskService.start(task.getTaskId());
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
	}
	
	/**
	 * 停止任务
	 * @see 2016-12-7	 
	 * @param taskId 任务 ID
	 * @return 状态码
	 * @since 1.0
	 */
	@RequestMapping(value = "/stop", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> stop(@RequestBody Task task) {
		try {
			taskService.stop(task.getTaskId());
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {}
		return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
	}
	
	/**
	 * 获得任务
	 * @param taskId 任务 ID
	 * @return Task 任务
	 * @since 1.0
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/getTasksByState/{state}", method = RequestMethod.GET)
	public ResponseEntity<List<Task>> getTasksByState(@PathVariable("state") String state) {
		try {
			List<Task> tasks = taskService.getTasksByState(state);
			return new ResponseEntity<>(HttpStatus.OK).accepted().body(tasks);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}
	
}
