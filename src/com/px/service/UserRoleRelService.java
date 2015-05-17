package com.px.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.px.common.service.CommonService;
import com.px.dao.UserRoleRelDao;
import com.px.entity.Role;
import com.px.entity.UserRoleRel;

@Service
public class UserRoleRelService extends CommonService<UserRoleRel,String>{

	@Autowired
	private UserRoleRelDao userRoleRelDao;

	@Autowired
	public void setUserRoleRelDao(UserRoleRelDao userRoleRelDao) {
		super.setCommonDao(userRoleRelDao);
	}
	
	/**
	 * 取某个用户的角色列表
	 * @param userId
	 * @return
	 */
	public List<Role> getRoleListByUser(String userId){
		return userRoleRelDao.getRoleListByUser(userId);
	}
}
