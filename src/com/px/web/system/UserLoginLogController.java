package com.px.web.system;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.px.common.entity.PageModel;
import com.px.entity.UserLoginLog;
import com.px.service.UserLoginLogService;

/**
 * 用户登录日志Controller
 * @author Jianfang Xu
 *
 */
@Controller
@RequestMapping("/boss/log")
public class UserLoginLogController {
	
	@Autowired
	private UserLoginLogService userLoginLogService;

	@RequestMapping("/list")
	public String list(HttpServletRequest request,Model model){
		String account = request.getParameter("account");
		String currentPageStr = request.getParameter("currentPage");
		String pageSizeStr = request.getParameter("pageSize");
		int currentPage = 1;
		int pageSize = 10;
		if(StringUtils.isNotBlank(currentPageStr)){
			currentPage = Integer.parseInt(currentPageStr);
		}
		if(StringUtils.isNotBlank(pageSizeStr)){
			pageSize = Integer.parseInt(pageSizeStr);
		}
		PageModel<UserLoginLog> page = userLoginLogService.queryForPage(account,1,currentPage, pageSize);
		
		model.addAttribute("page", page);
		model.addAttribute("account", account);
		
		return "system/loginlog/list";
	}
	
}
