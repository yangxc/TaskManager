package com.peraglobal.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.peraglobal.model.Task;

/**
 *  <code>TaskMapper.java</code>
 *  <p>功能:任务调度存储
 *  
 *  <p>Copyright 安世亚太 2016 All right reserved.
 *  @author yongqian.liu	
 *  @version 1.0
 *  @see 2016-12-2
 *  </br>最后修改人 无
 */
@Mapper
public interface TaskMapper {
	
	/**
	 * 根据页数查询任务列表
	 * @param pageNo 页数
	 * @return
	 */
	@Select("select * from task")
	public List<Task> getTasks();
	
	/**
	 * 根据组 ID 获得任务列表
	 * @param groupId 组 ID
	 * @return List<Task> 任务列表
	 */
	@Select("select * from task where groupId = #{groupId}")
    public List<Task> getTaskList(String groupId);
   
	/**
	 * 根据任务 ID 获得任务
	 * @param taskId 任务 ID
	 * @return Task 任务
	 */
    @Select("select * from task where taskId = #{taskId}")
    public Task getTask(String taskId);
   
	/**
	 * 根据任务名称和组 ID 获得任务
	 * @param taskName 任务名称
	 * @param groupId 组 ID
	 * @return Task 任务
	 */
    @Select("select * from task where taskName = #{taskName} and groupId = #{groupId}")
    public Task getTaskByTaskName(Task task);
    
	/**
	 * 创建任务
	 * @param task 任务对象
	 */
    @Insert("insert into task (taskId, taskName, groupId, groupName, startExpress, stopExpress, startCommand, stopCommand, taskState, createTime, updateTime) values (#{taskId}, #{taskName}, #{groupId}, #{groupName}, #{startExpress}, #{stopExpress}, #{startCommand}, #{stopCommand}, #{taskState}, #{createTime}, #{updateTime})")  
    public void createTask(Task task);

    /**
	 * 移除任务
	 * @param taskId 任务 ID
	 */
    @Delete("delete from task where taskId = #{taskId}")
	public void removeTask(String taskId);

	/**
	 * 编辑任务
	 * @param task 任务对象
	 */
    @Update("update task set taskName = #{taskName}, groupName = #{groupName}, startExpress = #{startExpress}, stopExpress = #{stopExpress}, startCommand = #{startCommand}, stopCommand = #{stopCommand}, updateTime = #{updateTime} where taskId = #{taskId}")
	public void editTask(Task task);

	/**
	 * 根据任务 ID 修改任务状态
	 * @param task 任务对象
	 */
    @Update("update task set taskState = #{taskState}, updateTime = #{updateTime} where taskId = #{taskId}")
	public int updateStateByTask(Task task);

	/**
	 * 根据任务状态查询任务列表
	 * @param stateStrat 任务状态
	 */
	@Select("select * from task where taskState = #{taskState}")
	public List<Task> getTaskByStart(String stateStrat);

	

}
