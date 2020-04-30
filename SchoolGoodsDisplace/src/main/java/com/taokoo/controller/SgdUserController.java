package com.taokoo.controller;

import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.taokoo.BasePageAccess;
import com.taokoo.exception.ParameterException;
import com.taokoo.model.JsonResult;
import com.taokoo.model.SgdUser;
import com.taokoo.service.SgdUserService;
import com.taokoo.util.MapValueUtil;
/**
 * 用户controller类
 * @author Taokoo
 * @date 2020/05/01
 */
@RestController
@RequestMapping("/user")
public class SgdUserController extends BasePageAccess {
	
	@Autowired
	private SgdUserService sgdUserService;
	
	@RequestMapping("/getUser/{id}")
	public String getUserById(@PathVariable int id){
		return sgdUserService.getUserById(id).getStuName();
	}

	/**
	 * 用户注册
	 * @param jsonStr
	 * @param request
	 * @param response
	 * @return
	 */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
	public JsonResult register(@RequestBody String jsonStr, HttpServletRequest request, HttpServletResponse response) {
//        Map<String, Object> map = null;
//        boolean rb = false;
//        String msg = "注册失败";
        JsonResult rs = new JsonResult();
        Map<String, Object> paramMap;
        try {
            paramMap = getParamMap(jsonStr);
            String userName = MapValueUtil.getString(paramMap, "username");
            String passWord = MapValueUtil.getString(paramMap, "password");
            String stuName = MapValueUtil.getString(paramMap, "stuname");
            String stuMail = MapValueUtil.getString(paramMap, "stumail");
            String stuPhone = MapValueUtil.getString(paramMap, "stuphone");
            String stuAddress = MapValueUtil.getString(paramMap, "stuaddress");
            SgdUser user = sgdUserService.getUserByName(userName);
            if(null != user) {
                rs.setStatus(false);
                rs.setMessage("此账号已注册");
                return rs;
            }
            user = new SgdUser();
            user.setId(UUID.randomUUID().toString());
            user.setUserName(userName);
            user.setPassWord(passWord);
            user.setStuName(stuName);
            user.setStuMail(stuMail);
            user.setStuPhone(stuPhone);
            user.setStuAddress(stuAddress);
            int count = sgdUserService.addUser(user);
            if(count > 0) {
                rs.setStatus(true);
                rs.setMessage("注册成功");
                return rs;
            }
        } catch (JSONException e) {
            rs.setStatus(false);
            rs.setMessage("参数格式错误");
            return rs;
        } catch (ParameterException e) {
            rs.setStatus(false);
            rs.setMessage(e.getMessage());
            return rs;
        } catch (Exception e) {
            rs.setStatus(false);
            rs.setMessage("系统错误");
            return rs;
        }
        rs.setStatus(false);
        rs.setMessage("注册失败");
	    return rs;
	}
	
}
