package com.peraglobal.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.peraglobal.model.Task;

@Mapper
public interface TaskMapper {
	
	
	@Select("select * from Task where groupId = #{groupId}")
    public List<Task> getTaskList(String groupId);
   
    @Select("select taskId, taskName, groupId from Task where taskId = #{taskId}")
    public Task getTask(String taskId);
   
    @Insert("insert into Task (taskId, taskName, groupId, startExpress, stopExpress, startCommand, stopCommand) values(#{taskId}, #{taskName}, #{groupId}, #{startExpress}, #{stopExpress}, #{startCommand}, #{stopCommand})")  
    public int createTask(Task task);

    @Delete("delete from Task where taskId = #{taskId}")
	public int removeTask(String taskId);

    @Update("update Task taskName = #{taskName}, groupId = #{groupId}, startExpress = #{startExpress}, stopExpress = #{stopExpress}, startCommand = #{startCommand}, stopCommand = #{stopCommand} where taskId = #{taskId}")
	public int editTask(String taskId);

    @Update("update Task taskName = #{taskName} where taskId = #{taskId}")
	public int renameTask(String taskId, String taskName);

    @Update("update Task taskState = '1' where taskId = #{taskId}")
	public int start(String taskId);

    @Update("update Task taskState = '2' where taskId = #{taskId}")
	public int stop(String taskId);
    
    @Select("select * from Task")
    public List<Task> getTaskAll();
    

}
