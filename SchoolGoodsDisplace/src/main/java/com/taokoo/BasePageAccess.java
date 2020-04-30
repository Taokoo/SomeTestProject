package com.taokoo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.json.JSONException;
import org.json.JSONObject;
import com.taokoo.model.SgdUser;


public class BasePageAccess {
	public final String all = ""; //检查页面页面权限
	public final String index = "index"; //不需要访问权限
	public final String function = "function"; //不检查权限，方法内部监查权限
	
	
	public static String sessionKey = "MEDIAWEBADMIN";
	
	public SgdUser getUser(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		SgdUser userModel = (SgdUser)session.getAttribute(sessionKey);	
		
		return userModel;
	}

	public String back2Login(HttpServletRequest request){
		return "redirect:/login";
	}
	
	public String back2Error(HttpServletRequest request, Exception e){
		return "redirect:/error";
	}
	
	
	public void ClearSession(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		
		if(session.getAttribute(sessionKey) != null)
			session.removeAttribute(sessionKey);
	}
	
	public void setGlobalPageParam(HttpServletRequest request,String pageTitle)
	{
		request.setAttribute("pageTitle", pageTitle);
		request.setAttribute("curPageName", pageTitle);
	}
	
	public Map<String, Object> getParamMap(String jsonStr) throws JSONException {
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
	
	public SgdUser getUser(String token)
	{
//	    SgdUser userModel = (SgdUser)MemcachedUtil.get(token); //TODO 从缓存中获取用户信息
		return new SgdUser();
	}
}
