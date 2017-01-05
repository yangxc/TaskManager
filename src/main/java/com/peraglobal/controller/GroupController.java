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

import com.peraglobal.model.TaskGroup;
import com.peraglobal.service.GroupService;

/**
 *  <code>GroupController.java</code>
 *  <p>功能:任务组 Controller
 *  <p>Copyright 安世亚太 2016 All right reserved.
 *  @author yongqian.liu	
 *  @version 1.0
 *  @see 2016-12-5
 *  </br>最后修改人 无
 */
@RestController
@RequestMapping("taskmanager/group")
public class GroupController {
	
	@Autowired
	private GroupService groupService;
	
	
	/**
	 * 获得组列表
	 * @param groupId 组Id （多用户区分不同用户）
	 * @return List<Group> 组列表
	 * @since 1.0
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/getTaskGroupList", method = RequestMethod.GET)
	public ResponseEntity<List<TaskGroup>> getTaskGroupList() {
		try {
			List<TaskGroup> taskGroups = groupService.getTaskGroupList();
			return new ResponseEntity<>(HttpStatus.OK).accepted().body(taskGroups);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	/**
	 * 根据父节点 ID 获得组列表
	 * @param groupId 组Id
	 * @return List<Group> 组列表
	 * @since 1.0
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/getTaskGroupsByParentId/{parentId}", method = RequestMethod.GET)
	public ResponseEntity<List<TaskGroup>> getTaskGroupsByParentId(@PathVariable("parentId") String parentId) {
		try {
			List<TaskGroup> taskGroups = groupService.getTaskGroupsByParentId(parentId);
			return new ResponseEntity<>(HttpStatus.OK).accepted().body(taskGroups);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	/**
	 * 获得组
	 * @param gorupId 组 ID
	 * @return gorup 组
	 * @since 1.0
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/getTaskGroup/{gorupId}", method = RequestMethod.GET)
	public ResponseEntity<TaskGroup> getTaskGroup(@PathVariable("gorupId") String gorupId) {
		try {
			TaskGroup taskGroup = groupService.getTaskGroup(gorupId);
			return new ResponseEntity<>(HttpStatus.OK).accepted().body(taskGroup);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	/**
	 * 创建组
	 * @param task 组对象
	 * @return taskId 创建成功返回组 ID
	 * @since 1.0
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/createTaskGroup", method = RequestMethod.POST)
	public ResponseEntity<String> createTaskGroup(@RequestBody TaskGroup taskGroup) {
		try {
			String groupId = groupService.createTaskGroup(taskGroup);
			if(groupId != null) {
				return new ResponseEntity<>(HttpStatus.CREATED).accepted().body(groupId);
			}
		} catch (Exception e) {}
		return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
	}
	
	/**
	 * 移除组
	 * @param groupId 组 ID
	 * @return 状态码
	 * @since 1.0
	 */
	@RequestMapping(value = "/removeTaskGroup/{groupId}", method = RequestMethod.DELETE)
	public ResponseEntity<?> removeTaskGroup(@PathVariable("groupId") String groupId) {
		try {
			groupService.removeTaskGroup(groupId);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {}
		return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
	}
	
	/**
	 * 编辑组
	 * @param group 组对象
	 * @return 状态码
	 * @since 1.0
	 */
	@RequestMapping(value = "/editTaskGroup", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> editTaskGroup(@RequestBody TaskGroup taskGroup) {
		try {
			groupService.editTaskGroup(taskGroup);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {}
		return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
	}
	
}
