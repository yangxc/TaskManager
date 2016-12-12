package com.peraglobal.model;

import java.util.Date;

/**
 *  <code>Group.java</code>
 *  <p>功能:组对象
 *  
 *  <p>Copyright 安世亚太 2016 All right reserved.
 *  @author yongqian.liu	
 *  @version 1.0
 *  @see 2016-12-12
 *  </br>最后修改人 无
 */
public class TaskGroup {

	/**
	 * @category 组 ID
	 */
	private String groupId;
	
	/**
	 * @category 组名称
	 */
	private String groupName;
	
	/**
	 * @category 父节点 ID
	 */
	private String parentId;
	
	/**
	 * @category 是否是叶子节点
	 */
	private String ifLeaf;

	/**
	 * @category 创建时间
	 */
	private Date createTime;
	
	/**
	 * @category 更新时间
	 */
	private Date updateTime;

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

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getIfLeaf() {
		return ifLeaf;
	}

	public void setIfLeaf(String ifLeaf) {
		this.ifLeaf = ifLeaf;
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
