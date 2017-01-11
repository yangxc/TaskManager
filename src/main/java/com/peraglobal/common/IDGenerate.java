package com.peraglobal.common;

import java.util.UUID;

/**
 * <code>IDGenerate.java</code>
 * <p>
 * 功能:UUID 生成类
 * <p>
 * Copyright 安世亚太 2016 All right reserved.
 * 
 * @author yongqian.liu
 * @version 1.0
 * @see 2016-12-6 </br>
 *      最后修改人 无
 */
public class IDGenerate {

	/**
	 * 主键生成策略uuid
	 * 
	 * @return
	 */
	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
