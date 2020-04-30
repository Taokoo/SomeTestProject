package com.taokoo.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.taokoo.exception.ParameterException;



public class MapValueUtil {

	public static String getString(Map<String, Object> paramMap, String key) throws ParameterException
	{
		String result  ="";
		
		if(paramMap.containsKey(key) && paramMap.get(key) != null)
		{
			try
			{
				result = String.valueOf(paramMap.get(key));
			} 
			catch (Exception e)
			{
				throw new ParameterException(ParameterException.getMsg(ParameterException.typeerror, key));//抛出异常  
			}
		}
		else
		{
			 throw new ParameterException(ParameterException.getMsg(ParameterException.nullerror, key));//抛出异常  
		}
			
		return result;
	}
	
	public static String getPhone(Map<String, Object> paramMap, String key) throws ParameterException
	{
		String result = getString(paramMap, key);
		
		if(!isPhone(result)){
			throw new ParameterException(ParameterException.getMsg(ParameterException.phoneerror, key));//抛出异常  
		}
		
		return result;
	}
	
	public static String getEmail(Map<String, Object> paramMap, String key) throws ParameterException
	{
		String result = getString(paramMap, key);
		if(!isEmail(result)){
			throw new ParameterException(ParameterException.getMsg(ParameterException.emailerror, key));//抛出异常
		}
		return result;
	}
	
	
	public static int getInt(Map<String, Object> paramMap, String key) throws ParameterException
	{
		int result;
		
		if(paramMap.containsKey(key) && paramMap.get(key) != null)
		{
			try
			{
				result = Integer.valueOf(paramMap.get(key).toString());
			} 
			catch (Exception e)
			{
				throw new ParameterException(ParameterException.getMsg(ParameterException.typeerror, key));//抛出异常  
			}
		}
		else
		{
			 throw new ParameterException(ParameterException.getMsg(ParameterException.nullerror, key));//抛出异常  
		}
			
		return result;
	}
	
	public static int[] getIntArray(Map<String, Object> paramMap, String key) throws ParameterException
	{	
		if(paramMap.containsKey(key) && paramMap.get(key) != null)
		{
			try
			{
				String resultStr = paramMap.get(key).toString();
				JSONArray arr = new JSONArray(resultStr);
				
				int[] result = new int[arr.length()];
				for (int i = 0; i < arr.length(); i++) {
					result[i] = arr.getInt(i);
				}
				
				return result;
			} 
			catch (Exception e)
			{
				throw new ParameterException(ParameterException.getMsg(ParameterException.typeerror, key));//抛出异常  
			}			
		}
		else
		{
			 throw new ParameterException(ParameterException.getMsg(ParameterException.nullerror, key));//抛出异常  
		}
					
	}
	
	public static List<String> getList(Map<String, Object> paramMap, String key) throws ParameterException{
		if(paramMap.containsKey(key) && paramMap.get(key) != null)
		{
			try
			{
				String resultStr = paramMap.get(key).toString();
//				resultStr = resultStr.substring(1, resultStr.length()-1).replaceAll(" ", "");//去掉首尾括号及所有空格
				return Arrays.asList(resultStr.split(","));
			} 
			catch (Exception e)
			{
				throw new ParameterException(ParameterException.getMsg(ParameterException.typeerror, key));//抛出异常  
			}			
		}
		else
		{
			 throw new ParameterException(ParameterException.getMsg(ParameterException.nullerror, key));//抛出异常  
		}
	}
	public static long getlong(Map<String, Object> paramMap, String key) throws ParameterException
	{
		long result;
		
		if(paramMap.containsKey(key) && paramMap.get(key) != null)
		{
			try
			{
				result = Long.valueOf(paramMap.get(key).toString());
			} 
			catch (Exception e)
			{
				throw new ParameterException(ParameterException.getMsg(ParameterException.typeerror, key));//抛出异常  
			}
		}
		else
		{
			 throw new ParameterException(ParameterException.getMsg(ParameterException.nullerror, key));//抛出异常  
		}
			
		return result;
	}
	
	public static Double getDouble(Map<String, Object> paramMap, String key) throws ParameterException
	{
		Double result;
		
		if(paramMap.containsKey(key) && paramMap.get(key) != null)
		{
			try
			{
				result = Double.valueOf(paramMap.get(key).toString());
			} 
			catch (Exception e)
			{
				throw new ParameterException(ParameterException.getMsg(ParameterException.typeerror, key));//抛出异常  
			}
		}
		else
		{
			 throw new ParameterException(ParameterException.getMsg(ParameterException.nullerror, key));//抛出异常  
		}
			
		return result;
	}
	
	public static void putValue(Map<String, Object> paramMap, String key, double dv)
	{	
		if(Double.isInfinite(dv) || Double.isNaN(dv)){
			paramMap.put(key, 0);
		}
		else {
			paramMap.put(key, dv);
		}
			
	}
	
	public static Map<String, Object> getParamMap(String jsonStr) throws JSONException {
		JSONObject jsObj = new JSONObject(jsonStr);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String key;
		Iterator<?> jsonParamKeys = jsObj.keys();
		while (jsonParamKeys.hasNext()) {
			key = String.valueOf(jsonParamKeys.next());
			paramMap.put(key, jsObj.get(key));
		}
		return paramMap;
	}
	
	public static boolean isPhone(String phone) {
	    String regex = "^(1)\\d{10}$";
	    if (phone.length() != 11) {
	     
	        return false;
	    } else {
	        Pattern p = Pattern.compile(regex);
	        Matcher m = p.matcher(phone);
	        boolean isMatch = m.matches();

	        return isMatch;
	    }
	}
	public static boolean isEmail(String email){
	    String regex = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
	    
	        Pattern p = Pattern.compile(regex);
	        Matcher m = p.matcher(email);
	        boolean isMatch = m.matches();

	        return isMatch;
	}
}
