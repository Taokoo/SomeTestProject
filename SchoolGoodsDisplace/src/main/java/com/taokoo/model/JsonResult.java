package com.taokoo.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import com.taokoo.util.UtilConstants;


public class JsonResult {

	private boolean status;
	private String message;
	private Object result;
	private String code;
	
	public JsonResult(Object result, boolean status, String msg) {
		this.result = status ? result : null;
		this.status = status;
		this.message = msg;
		this.code = UtilConstants.HSTATUS_200;
	}
	
	public JsonResult(Object result, boolean status,  String code, String msg) {
		this.result = status ? result : null;
		this.status = status;
		this.message = msg;
		this.code = code;
	}

	public JsonResult() {

	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Map<String, Object> result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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
}