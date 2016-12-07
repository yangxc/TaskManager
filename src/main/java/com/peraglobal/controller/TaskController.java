package com.peraglobal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.peraglobal.model.Task;
import com.peraglobal.service.TaskService;
import com.peraglobal.utils.JsonResult;
import com.peraglobal.utils.ResultCode;

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
	 * @return JsonResult
	 */
	@RequestMapping(value = "/getTaskList")
	@ResponseBody
	public JsonResult getTaskList(String groupId) {
		JsonResult result = new JsonResult();
		
		List<Task> tasks = taskService.getTaskList(groupId);
		
		result.setMessage("查询任务列表成功！");
		result.setCode(ResultCode.SUCCESS);
		result.setData(tasks);
		return result;
	}
	
	/**
	 * 获得任务
	 * @param taskId 任务 ID
	 * @return JsonResult
	 */
	@RequestMapping(value = "/getTask")
	@ResponseBody
	public JsonResult findCrawler(String taskId) {
		JsonResult result = new JsonResult();
		
		Task task = taskService.getTask(taskId);
		
		result.setMessage("查询任务成功！");
		result.setCode(ResultCode.SUCCESS);
		result.setData(task);
		return result;
	}
	
	/**
	 * 创建任务
	 * @param groupId 组 ID
	 * @param taskName 任务名称
	 * @param startExpress 启动规则表达式
	 * @param stopExpress 停止规则表达式 
	 * @param startCommand 启动命令
	 * @param stopCommand 停止命令
	 * @return taskId 任务 ID
	 */
	@RequestMapping(value = "/createTask")
	@ResponseBody
	public JsonResult createTask(String groupId, String taskName, String startExpress, String stopExpress, String startCommand, String stopCommand) {
		JsonResult result = new JsonResult();
		
		Task task = new Task();
		task.setTaskId(java.util.UUID.randomUUID().toString());
		task.setTaskName(taskName);
		task.setGroupId(groupId);
		task.setStartExpress(startExpress);
		task.setStopExpress(stopExpress);
		task.setStartCommand(startCommand);
		task.setStopCommand(stopCommand);
		taskService.createTask(task);
		
		result.setMessage("创建任务成功！");
		result.setCode(ResultCode.SUCCESS);
		result.setData(task);
		return result;
	}
	
	/**
	 * 移除任务
	 * @param taskId 任务 ID
	 */
	@RequestMapping(value = "/removeTask")
	@ResponseBody
	public JsonResult removeTask(String taskId) {
		JsonResult result = new JsonResult();
		
		taskService.removeTask(taskId);
		
		result.setMessage("移除任务成功！");
		result.setCode(ResultCode.SUCCESS);
		return result;
	}
	
	/**
	 * 编辑任务
	 * @param taskId 任务 ID
	 * @param taskName 任务名称
	 * @param startExpress 启动规则表达式
	 * @param stopExpress 停止规则表达式 
	 * @param startCommand 启动命令
	 * @param stopCommand 停止命令
	 */
	@RequestMapping(value = "/editTask")
	@ResponseBody
	public JsonResult editTask(String taskId, String taskName, String startExpress, String stopExpress, String startCommand, String stopCommand) {
		JsonResult result = new JsonResult();
		
		Task task = new Task();
		task.setTaskId(taskId);
		task.setTaskName(taskName);
		task.setStartExpress(startExpress);
		task.setStopExpress(stopExpress);
		task.setStartCommand(startCommand);
		task.setStopCommand(stopCommand);
		taskService.editTask(task);
		
		result.setMessage("编辑任务成功！");
		result.setCode(ResultCode.SUCCESS);
		return result;
	}
	
	/**
	 * 修改任务名称
	 * @param taskId 任务 ID
	 * @param taskName 任务名称
	 */
	@RequestMapping(value = "/renameTask")
	@ResponseBody
	public JsonResult renameTask(String taskId, String taskName) {
		JsonResult result = new JsonResult();
		
		taskService.renameTask(taskId, taskName);
		
		result.setMessage("编辑任务成功！");
		result.setCode(ResultCode.SUCCESS);
		return result;
	}
	
	/**
	 * 开始任务
	 * @param taskId 任务 ID
	 * @return msg 消息：1.启动成功，2.启动失败，失败原因
	 */
	@RequestMapping(value = "/start")
	@ResponseBody
	public JsonResult start(String taskId) {
		JsonResult result = new JsonResult();
		
		taskService.start(taskId);
		
		result.setMessage("启动成功！");
		result.setCode(ResultCode.SUCCESS);
		return result;
	}
	
	/**
	 * 重启任务
	 * @param taskId 任务 ID
	 * @return msg 消息：1.启动成功，2.启动失败，失败原因
	 */
	@RequestMapping(value = "/reset")
	@ResponseBody
	public JsonResult reset(String taskId) {
		JsonResult result = new JsonResult();
		
		// taskService.reset(taskId);
		
		result.setMessage("重启成功！");
		result.setCode(ResultCode.SUCCESS);
		return result;
	}
	
	/**
	 * 停止任务
	 * @param taskId 任务 ID
	 */
	public JsonResult stop(String taskId) {
		JsonResult result = new JsonResult();
		
		taskService.stop(taskId);
		
		result.setMessage("停止成功！");
		result.setCode(ResultCode.SUCCESS);
		return result;
	}
}
