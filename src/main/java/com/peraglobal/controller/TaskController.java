package com.peraglobal.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  <code>TaskController.java</code>
 *  <p>功能:任务调度器Controller
 *  
 *  <p>Copyright 安世亚太 2016 All right reserved.
 *  @author yongqian.liu	
 *  @version 1.0 
 *  </br>最后修改人 无
 */
@RequestMapping("task")
@RestController
public class TaskController {
	

	/**
	 * 获得任务列表
	 * @param groupId 组Id （多用户区分不同用户）
	 * @return Json
	 */
	@RequestMapping("getTaskList")
	public String getTaskList(String groupId) {
		return "{task:{'taskId':'10011','taskName':'sina.com'},task:{'taskId':'10011','taskName':'sina.com'},task:{'taskId':'10011','taskName':'sina.com'}}";
	}

}
