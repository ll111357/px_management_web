package com.px.dao;

import com.px.common.entity.PageModel;
import com.px.entity.UserLoginLog;

public interface UserLoginLogDaoCustom {

	/**
	 * type为1为管理员
	 * @param account
	 * @param type
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	PageModel<UserLoginLog> queryForPage(String account,Integer type,Integer currentPage,Integer pageSize);
	
}
