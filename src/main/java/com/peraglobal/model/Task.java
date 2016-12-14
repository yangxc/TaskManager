package com.peraglobal.model;

import java.io.Serializable;
import java.util.Date;

/**
 *  <code>Task.java</code>
 *  <p>功能:任务对象
 *  
 *  <p>Copyright 安世亚太 2016 All right reserved.
 *  @author yongqian.liu	
 *  @version 1.0
 *  @see 2016-12-2
 *  </br>最后修改人 无
 */
public class Task  implements Serializable {

	private static final long serialVersionUID = 3691967820839546108L;

	/**
	 * @category 任务 ID
	 */
	private String taskId;
	
	/**
	 * @category 任务名称
	 */
	private String taskName;
	
	/**
	 * @category 组 ID
	 */
	private String groupId;
	
	/**
	 * @category 组名称
	 */
	private String groupName;

	/**
	 * @category 开始时间表达式
	 */
	private String startExpress;
	
	/**
	 * @category 停止时间表达式
	 */
	private String stopExpress;
	
	/**
	 * @category 开始命令
	 */
	private String startCommand;
	
	/**
	 * @category 结束命令
	 */
	private String stopCommand;
	
	/**
	 * @category 状态
	 */
	private String taskState;
	
	/**
	 * @category 创建时间
	 */
	private Date createTime;
	
	/**
	 * @category 更新时间
	 */
	private Date updateTime;

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getStartExpress() {
		return startExpress;
	}

	public void setStartExpress(String startExpress) {
		this.startExpress = startExpress;
	}

	public String getStopExpress() {
		return stopExpress;
	}

	public void setStopExpress(String stopExpress) {
		this.stopExpress = stopExpress;
	}

	public String getStartCommand() {
		return startCommand;
	}

	public void setStartCommand(String startCommand) {
		this.startCommand = startCommand;
	}

	public String getStopCommand() {
		return stopCommand;
	}

	public void setStopCommand(String stopCommand) {
		this.stopCommand = stopCommand;
	}

	public String getTaskState() {
		return taskState;
	}

	public void setTaskState(String taskState) {
		this.taskState = taskState;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}
