package com.peraglobal.model;

/**
 *  <code>TaskConst.java</code>
 *  <p>功能：常量类
 *  
 *  <p>Copyright 安世亚太 2016 All right reserved.
 *  @author yongqian.liu	
 *  @version 1.0
 *  @see 2016-12-7
 *  </br>最后修改人 无
 */
public class TaskConst {
	
	/**
	 * @category 任务 Key
	 */
	public static final String TASK_KEY = "task_key";
	
	/**
	 * @category 开始触发器 ID 标识
	 */
	public static final String TRIGGER_STRAT = "trigger_strat";
	
	/**
	 * @category 结束触发器 ID 标识
	 */
	public static final String TRIGGER_STOP = "trigger_stop";

	/**
	 * @category 任务状态：就绪
	 */
	public static final String STATE_READY = "0";
	
	/**
	 * @category 任务状态：开始
	 */
	public static final String STATE_STRAT = "1";
	
	/**
	 * @category 任务状态：结束
	 */
	public static final String STATE_STOP = "2";
	
	/**
	 * @category 任务状态：禁用
	 */
	public static final String STATE_FORBIDDEN = "3";
	
	/**
	 * @category 任务状态：就绪
	 */
	public static final String STATE_READY_TO = "就绪";
	
	/**
	 * @category 任务状态：开始
	 */
	public static final String STATE_STRAT_TO = "运行";
	
	/**
	 * @category 任务状态：结束
	 */
	public static final String STATE_STOP_TO = "结束";
	
	/**
	 * @category 任务状态：禁用
	 */
	public static final String STATE_FORBIDDEN_TO = "禁用";
	
	/**
	 * @category 是否叶子节点：0.否；
	 */
	public static final String No = "0";
	
	/**
	 * @category 是否叶子节点：1.是；
	 */
	public static final String YES = "1";
	

}
