package com.px.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.px.common.dao.CommonDao;
import com.px.entity.Resource;
import com.px.entity.RoleResourceRel;

public interface RoleResourceRelDao extends CommonDao<RoleResourceRel,String>{

	@Query("select r.resource from RoleResourceRel r where r.role.id = ?1 ")
	List<Resource> findResourceListByRole(String roleId);
	
}
