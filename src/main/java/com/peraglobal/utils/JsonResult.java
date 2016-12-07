package com.peraglobal.utils;

/**
 *  <code>JsonResult.java</code>
 *  <p>功能: Json 对象封装
 *  
 *  <p>Copyright 安世亚太 2016 All right reserved.
 *  @author yongqian.liu	
 *  @version 1.0
 *  2016-12-7
 *  </br>最后修改人 无
 */
public class JsonResult {

	private String code;
	private String message;
	private Object data;
	
	public JsonResult() {
		this.setCode(ResultCode.SUCCESS);
		this.setMessage("成功！");
		
	}
	
	public JsonResult(ResultCode code) {
		this.setCode(code);
		this.setMessage(code.msg());
	}
	
	public JsonResult(ResultCode code, String message) {
		this.setCode(code);
		this.setMessage(message);
	}
	
	public JsonResult(ResultCode code, String message, Object data) {
		this.setCode(code);
		this.setMessage(message);
		this.setData(data);
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(ResultCode code) {
		this.code = code.val();
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
