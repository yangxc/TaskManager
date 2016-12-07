package com.peraglobal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
@RequestMapping("task")
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	
	/**
	 * 获得任务列表
	 * @param groupId 组Id （多用户区分不同用户）
	 * @return ResponseEntity
	 */
	@RequestMapping(value = "/getTaskList", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getTaskList(@RequestBody String groupId) {
		try {
			return new ResponseEntity<>(taskService.getTaskList(groupId), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	/**
	 * 获得任务
	 * @param taskId 任务 ID
	 * @return ResponseEntity
	 */
	@RequestMapping(value = "/getTask", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getTask(@RequestBody String taskId) {
		try {
			return new ResponseEntity<>(taskService.getTask(taskId), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	/**
	 * 创建任务
	 * @param task 任务对象
	 * @return ResponseEntity
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/createTask", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createTask(@RequestBody Task task) {
		try {
			String taskId = taskService.createTask(task);
			if(taskId == null) {
				return new ResponseEntity<>(HttpStatus.CREATED).accepted().body(taskId);
			}
		} catch (Exception e) {}
		return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
	}
	
	/**
	 * 移除任务
	 * @param taskId 任务 ID
	 * @return ResponseEntity
	 */
	@RequestMapping(value = "/removeTask/{taskId}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> removeTask(@RequestBody String taskId) {
		try {
			taskService.removeTask(taskId);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {}
		return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
	}
	
	/**
	 * 编辑任务
	 * @param task 任务对象
	 * @return ResponseEntity
	 */
	@RequestMapping(value = "/editTask", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> editTask(@RequestBody Task task) {
		try {
			taskService.editTask(task);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {}
		return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
	}
	
	/**
	 * 修改任务名称
	 * @param task 任务对象
	 * @return ResponseEntity
	 */
	@RequestMapping(value = "/renameTask", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> renameTask(@RequestBody Task task) {
		try {
			taskService.renameTask(task);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {}
		return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
	}
	
	/**
	 * 开始任务
	 * @param taskId 任务 ID
	 * @return ResponseEntity
	 */
	@RequestMapping(value = "/start/{taskId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> start(@RequestBody String taskId) {
		try {
			taskService.start(taskId);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {}
		return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
	}
	
	/**
	 * 停止任务
	 * @param taskId 任务 ID
	 * @return ResponseEntity
	 */
	@RequestMapping(value = "/stop/{taskId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> stop(@RequestBody String taskId) {
		try {
			taskService.stop(taskId);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {}
		return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
	}
}
