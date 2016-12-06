package com.peraglobal.model;

public class Task {

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
	
	
	
	
}
