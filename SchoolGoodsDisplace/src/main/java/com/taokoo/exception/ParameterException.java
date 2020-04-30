package com.taokoo.exception;

public class ParameterException extends Exception  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	public static final int nullerror = 1;
	public static final int typeerror = 2;
	public static final int phoneerror = 3;
	public static final int emailerror = 4;
	
	public ParameterException(String msg){
		super(msg);
	}
	
	public static String getMsg(int type, String key)
	{
		String msg = "参数错误：未知错误";
		
		if(type == nullerror)
			msg = "参数错误：缺少参数 "+ key;
		else if(type == typeerror)
			msg = "参数错误：参数 "+ key+ " 类型错误";
		else if(type == phoneerror)
			msg = "请输入正确的手机号";
		else if(type == emailerror)
			msg = "请输入正确的邮箱";
		return msg;
	}
	
}
