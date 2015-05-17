package com.px.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.px.common.dao.CommonDao;
import com.px.entity.Role;
import com.px.entity.UserRoleRel;

public interface UserRoleRelDao  extends CommonDao<UserRoleRel,String>{

	 @Query("select r.role from UserRoleRel r where r.user.id = ?1 ")
	 List<Role> getRoleListByUser(String userId);
}
