package com.peraglobal.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peraglobal.common.IDGenerate;
import com.peraglobal.mapper.GroupMapper;
import com.peraglobal.model.TaskGroup;
import com.peraglobal.model.TaskConst;

/**
 *  <code>GroupService.java</code>
 *  <p>功能:组功能 Service
 *  
 *  <p>Copyright 安世亚太 2016 All right reserved.
 *  @author yongqian.liu	
 *  @version 1.0
 *  2016-12-5
 *  </br>最后修改人 无
 */
@Service
public class GroupService {

	@Autowired
    private GroupMapper groupMapper;
	
	/**
	 * 获得组列表
	 * @return List<Group> 组列表
	 */
	public List<TaskGroup> getTaskGroupList() throws Exception {
		return groupMapper.getTaskGroupList();
	}
	
	/**
	 * 根据父节点 ID 获得组列表
	 * @param parentId 父节点 ID
	 * @return Group 组列表
	 */
	public List<TaskGroup> getTaskGroupsByParentId(String parentId) throws Exception {
		return groupMapper.getTaskGroupsByParentId(parentId);
	}
	
	/**
	 * 获得组
	 * @param groupId ID
	 * @return Group 组
	 */
	public TaskGroup getTaskGroup(String groupId) throws Exception {
		return groupMapper.getTaskGroup(groupId);
	}

	/**
	 * 创建组
	 * @param group 组对象
	 * @return group 组 ID
	 * @throws Exception
	 */
	public String createTaskGroup(TaskGroup taskGroup) throws Exception {
		// 根据当前组名称和组 ID 查询组是否存在，则不创建
		TaskGroup group = groupMapper.getTaskGroupByGroup(taskGroup);
		if(group == null) {
			// 更新父节点状态
			TaskGroup parentGroup = groupMapper.getTaskGroup(taskGroup.getParentId());
			if(parentGroup != null) {
				parentGroup.setUpdateTime(new Date());
				parentGroup.setIfLeaf(TaskConst.No);
				groupMapper.editTaskGroup(parentGroup);
			}
			
			taskGroup.setGroupId(IDGenerate.uuid());
			taskGroup.setIfLeaf(TaskConst.YES); // 设置当前节点为叶子节点
			taskGroup.setCreateTime(new Date());
			taskGroup.setUpdateTime(new Date());
			groupMapper.createTaskGroup(taskGroup);
			return taskGroup.getGroupId();
		}
		return null;
	}

	/**
	 * 通过组 ID删除对象
	 * @param groupId 组 ID
	 * @throws Exception
	 */
	public void removeTaskGroup(String groupId) throws Exception {
		// 更新父节点标识
		TaskGroup group = groupMapper.getTaskGroup(groupId);
		List<TaskGroup> groupList = groupMapper.getTaskGroupsByParentId(group.getParentId());
		if(groupList != null && groupList.size() == 1) {
			group.setIfLeaf(TaskConst.YES);
			group.setUpdateTime(new Date());
			groupMapper.editTaskGroup(group);
		}
		
		// 删除当前节点
		groupMapper.removeTaskGroup(groupId);
	}

	/**
	 * 编辑组对象
	 * @param gorup 组对象
	 * @throws Exception
	 */
	public void editTaskGroup(TaskGroup taskGroup) throws Exception {
		TaskGroup group = groupMapper.getTaskGroup(taskGroup.getGroupId());
		group.setGroupName(taskGroup.getGroupName());
		group.setUpdateTime(new Date());
		groupMapper.editTaskGroup(group);
	}

}
