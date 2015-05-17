package com.px.dao;

import org.apache.commons.lang3.StringUtils;

import com.px.common.dao.CustomBaseSqlDaoImpl;
import com.px.common.entity.PageModel;
import com.px.entity.UserLoginLog;

public class UserLoginLogDaoImpl extends CustomBaseSqlDaoImpl implements UserLoginLogDaoCustom{

	public PageModel<UserLoginLog> queryForPage(String account,Integer type,Integer currentPage,Integer pageSize){
		String hql = "select l from UserLoginLog l where 1=1 ";
		if(type != 1){
			hql+=" and l.user.username = '"+account+"' ";
		}
		if(StringUtils.isNotBlank(account)){
			hql+=" and l.user.username like '%"+account+"%' ";
		}
		hql+=" order by l.createDate desc ";
		return this.queryForPage(hql, currentPage, pageSize);
	}
	
}
