package com.peraglobal.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.peraglobal.model.TaskRule;


@Mapper
public interface TaskRuleMapper {
	
    @Select("select * from taskrule where taskId = #{taskId}")
    public TaskRule getTaskRule(String taskId);
   
    @Insert("insert into taskrule (ruleId, taskId, express) values (#{ruleId}, #{taskId}, #{express,javaType=string,jdbcType=BLOB})")  
    public void createTaskRule(TaskRule taskRule);

    @Delete("delete from taskrule where taskId = #{taskId}")
	public void removeTaskRule(String taskId);

    @Update("update taskrule set express = #{express,javaType=string,jdbcType=BLOB} where taskId = #{taskId}")
	public void editTaskRule(TaskRule taskRule);

}
