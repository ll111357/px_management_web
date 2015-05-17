package com.px.dao;

import org.springframework.data.jpa.repository.Query;

import com.px.common.dao.CommonDao;
import com.px.entity.User;

public interface UserDao extends SystemUserDao,CommonDao<User,String>{

	@Query("select u from User u where u.username=?1 ")
	User findUserByName(String userName);
	
}
