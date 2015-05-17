package com.px.dao;

import com.px.common.dao.CustomBaseSqlDaoImpl;
import com.px.common.entity.PageModel;
import com.px.entity.User;

public class UserDaoImpl extends CustomBaseSqlDaoImpl implements SystemUserDao{
	
	public PageModel<User> queryForPage(int currentPage,int pageSize){
		String hql="select u from User u ";
		return this.queryForPage(hql, currentPage, pageSize);
	}
	
}
