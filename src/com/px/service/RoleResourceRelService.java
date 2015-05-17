package com.px.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.px.common.service.CommonService;
import com.px.dao.RoleResourceRelDao;
import com.px.entity.Resource;
import com.px.entity.RoleResourceRel;

@Service
public class RoleResourceRelService extends CommonService<RoleResourceRel,String>{

	@Autowired
	private RoleResourceRelDao roleResourceRelDao;

	@Autowired
	public void setRoleResourceRelDao(RoleResourceRelDao roleResourceRelDao) {
		super.setCommonDao(roleResourceRelDao);
	}
	
	/**
	 * 根据角色ID找资源
	 * @param roleId
	 * @return
	 */
	public List<Resource> findResourceListByRole(String roleId){
		List<Resource> resourceList = roleResourceRelDao.findResourceListByRole(roleId);
		return resourceList;
	}
}
