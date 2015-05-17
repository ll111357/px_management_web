package com.px.web.boss;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.px.entity.User;
import com.px.entity.UserLoginLog;
import com.px.service.UserLoginLogService;
import com.px.service.UserService;
import com.px.util.AddressUtils;
import com.px.util.Md5Util;
/**
 * 后台登陆首页Controller
 * @author Jeff Xu
 *
 */
@Controller
@RequestMapping("/boss")
public class IndexController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserLoginLogService userLoginLogService;
	
	//后台用户信息
	public final static String USER_SESSION_INFO = "user_session_info";
	
	//登陆错误信息
	public final static String LOGIN_ERROR_INFO = "login_error_info";

	@RequestMapping("/login")
	public String login(){
		return "login";
	}
	
	/**
	 * 登陆
	 */
	@RequestMapping("/doLogin")
	public String doLogin(HttpServletRequest request){
		String userName = request.getParameter("userName");
		String pwd = request.getParameter("pwd");
		String errorMsg = "";
		if(StringUtils.isNotBlank(userName) && StringUtils.isNotBlank(pwd)){
			User user = userService.findUserByName(userName);
			if(user != null){
				if(Md5Util.validatePassword(user.getPassword(), pwd)){
					insertLoginLog(user,UserLoginLog.EVENT_LOGIN,request);
					request.getSession().setAttribute(USER_SESSION_INFO, user);
					return "redirect:/boss/index";
				}else{
					errorMsg = "密码不正确!";
					request.getSession().setAttribute(LOGIN_ERROR_INFO, errorMsg);
					return "redirect:/boss/login";
				}
			}else{
				errorMsg = "用户名不正确!";
				request.getSession().setAttribute(LOGIN_ERROR_INFO, errorMsg);
				return "redirect:/boss/login";
			}
		}else{
			errorMsg = "用户名或者密码不能为空!";
			request.getSession().setAttribute(LOGIN_ERROR_INFO, errorMsg);
			return "redirect:/boss/login";
		}
	}
	
	/**
	 * 菜单页
	 * @return
	 */
	@RequestMapping("/menu")
	public String menu(){
		
		return "menu";
	}
	
	/**
	 * 跳转到Top页面
	 * @return
	 */
	@RequestMapping("/top")
	public String top(){
		return "top";
	}
	
	/**
	 * 跳转到main页面
	 * @return
	 */
	@RequestMapping("/main")
	public String main(){
		
		return "main";
	}
	
	/**
	 * 后台首页
	 * @return
	 */
	@RequestMapping("/index")
	public String index(){
	
		return "index";
	}
	
	/**
	 * 保存用户登陆登出明细记录
	 * @param user
	 * @param event
	 */
	private void insertLoginLog(User user,String event,HttpServletRequest request){
		UserLoginLog userLoginLog = new UserLoginLog();
		userLoginLog.setUser(user);
		//userLoginLog.setEvenet_type(event);
		userLoginLog.setLoginIp(AddressUtils.getIp(request));
		userLoginLog.setCreateDate(new Date());
		
		userLoginLogService.save(userLoginLog);
	}
	
}
