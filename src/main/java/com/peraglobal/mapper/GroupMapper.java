package com.peraglobal.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.peraglobal.model.TaskGroup;
import com.peraglobal.model.Task;

/**
 *  <code>TaskMapper.java</code>
 *  <p>功能:组调度存储
 *  
 *  <p>Copyright 安世亚太 2016 All right reserved.
 *  @author yongqian.liu	
 *  @version 1.0
 *  @see 2016-12-2
 *  </br>最后修改人 无
 */
@Mapper
public interface GroupMapper {
	
	/**
	 * 获得组列表
	 * @return List<Group> 组列表
	 */
	@Select("select * from TaskGroup")
    public List<TaskGroup> getTaskGroupList();
   
	/**
	 * 根据父节点 ID 获得组列表
	 * @param parentId 父节点 ID
	 * @return Group 组列表
	 */
	@Select("select * from TaskGroup where parentId = #{parentId}")
    public List<TaskGroup> getTaskGroupsByParentId(String parentId);
	
	/**
	 * 获得组
	 * @param groupId ID
	 * @return Group 组
	 */
	@Select("select * from TaskGroup where groupId = #{groupId}")
    public TaskGroup getTaskGroup(String groupId);
   
	/**
	 * 根据组名称和组 ID 获得组
	 * @param taskName 组名称
	 * @param groupId 组 ID
	 * @return Task 组
	 */
    @Select("select * from TaskGroup where parentId = #{parentId} and groupName = #{groupName}")
    public TaskGroup getTaskGroupByGroup(TaskGroup taskGroup);
    
	/**
	 * 创建组
	 * @param group 组对象
	 */
    @Insert("insert into TaskGroup (groupId, groupName, parentId, ifLeaf, createTime, updateTime) values (#{groupId}, #{groupName}, #{parentId}, #{ifLeaf}, #{createTime}, #{updateTime})")  
    public void createTaskGroup(TaskGroup taskGroup);

    /**
	 * 移除组
	 * @param groupId 组 ID
	 */
    @Delete("delete from TaskGroup where groupId = #{groupId}")
	public void removeTaskGroup(String groupId);

	/**
	 * 编辑组
	 * @param group 组对象
	 */
    @Update("update TaskGroup set groupName = #{groupName}, updateTime = #{updateTime} where groupId = #{groupId}")
	public void editTaskGroup(TaskGroup taskGroup);

}
