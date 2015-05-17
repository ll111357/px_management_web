package com.px.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.px.common.service.CommonService;
import com.px.dao.RoleDao;
import com.px.entity.Role;

@Service
public class RoleService extends CommonService<Role,String>{

	@Autowired
	private RoleDao roleDao;

	@Autowired
	public void setRoleDao(RoleDao roleDao) {
		super.setCommonDao(roleDao);
	}
	
	
	
}
