package com.px.dao;

import com.px.common.entity.PageModel;
import com.px.entity.User;

public interface SystemUserDao {
	
	PageModel<User> queryForPage(int currentPage,int pageSize);
	
}
