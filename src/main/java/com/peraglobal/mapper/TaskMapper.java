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
	
	
	@Select("select * from Task where groupId = #{groupId}")
    public List<Task> getTaskList(String groupId);
   
    @Select("select taskId, taskName, groupId from Task where taskId = #{taskId}")
    public Task getTask(String taskId);
   
    @Select("select taskId from Task where taskName = #{taskName} and groupId = #{groupId}")
    public String getTaskByTaskName(String taskName, String groupId);
    
    @Insert("insert into Task (taskId, taskName, groupId, startExpress, stopExpress, startCommand, stopCommand) values(#{taskId}, #{taskName}, #{groupId}, #{startExpress}, #{stopExpress}, #{startCommand}, #{stopCommand})")  
    public int createTask(Task task);

    @Delete("delete from Task where taskId = #{taskId}")
	public int removeTask(String taskId);

    @Update("update Task taskName = #{taskName}, groupId = #{groupId}, startExpress = #{startExpress}, stopExpress = #{stopExpress}, startCommand = #{startCommand}, stopCommand = #{stopCommand} where taskId = #{taskId}")
	public int editTask(Task task);

    @Update("update Task taskName = #{taskName} where taskId = #{taskId}")
	public int renameTask(String taskId, String taskName);

    @Update("update Task taskState = #{taskState} where taskId = #{taskId}")
	public int updateStateByTaskId(String state, String taskId);

    @Select("select * from Task")
    public List<Task> getTaskAll();
    
    
    

}
