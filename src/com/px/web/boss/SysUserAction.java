package com.px.web.boss;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.px.entity.User;
import com.px.service.UserService;
import com.px.util.Md5Util;

@Controller
@RequestMapping("/boss")
public class SysUserAction {
	
	@Autowired
	private UserService userService;

	@RequestMapping("/sysuser/list")
	public String list(){
		
		return "boss/system/user";
	}
	
	@RequestMapping("/sysuser/ajax_list")
	@ResponseBody
	public List<Map> findUserList(HttpServletRequest request){
		String userName = request.getParameter("userName");
		String realName = request.getParameter("realName");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		
		/*List<Map> userList = userService.findUserList(userName,realName,startDate,endDate);
		return userList;*/
		return new ArrayList<Map>();
	}
	
	@RequestMapping("/sysuser/ajax_save")
	@ResponseBody
	public Map saveUser(HttpServletRequest request) throws IOException{
		Map result = new HashMap();
		result.put("sucess", false);
		
		request.setCharacterEncoding("UTF-8");
		
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String realName = request.getParameter("realName");
		password=Md5Util.generatePassword(password);
		
		try{
			User user = new User();
			user.setUsername(userName);
			user.setPassword(password);
			user.setRealName(realName);
			user.setCreateDate(new Date());
			user.setUpdateDate(new Date());
			
			userService.save(user);
			result.put("sucess", true);
		}catch(Exception e){
			e.printStackTrace();
		}

		return result;
	}
	
	@RequestMapping("/sysuser/ajax_delete")
	@ResponseBody
	public Map deleteUser(HttpServletRequest request){
		Map result = new HashMap();
		result.put("sucess", false);
		
		String ids = request.getParameter("ids");
		
		if(ids != null && !ids.equals("")){
			String[] idsArray = ids.split(";");
			if(idsArray != null && idsArray.length >0 ){
				for(int i=0;i<idsArray.length;i++){
					userService.delete(idsArray[i]);
				}
			}
			result.put("sucess", true);
		}
		
		return result;
		
	}
	
}
