package com.px.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.px.common.entity.PageModel;
import com.px.common.service.CommonService;
import com.px.dao.UserLoginLogDao;
import com.px.entity.UserLoginLog;

@Service
public class UserLoginLogService extends CommonService<UserLoginLog,String>{

	@Autowired
	private UserLoginLogDao userLoginLogDao;

	@Autowired
	public void setUserLoginLogDao(UserLoginLogDao userLoginLogDao) {
		super.setCommonDao(userLoginLogDao);
	}	
	
	/**
	 * 查询某个人的登陆日志，type为1时为管理员：能查看所有人的日志
	 * @param account
	 * @param type
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public PageModel<UserLoginLog> queryForPage(String account,Integer type,Integer currentPage,Integer pageSize){
		return this.userLoginLogDao.queryForPage(account,type,currentPage, pageSize);
	}
	
}
