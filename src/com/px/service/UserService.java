package com.px.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.px.common.entity.PageModel;
import com.px.common.service.CommonService;
import com.px.dao.UserDao;
import com.px.entity.User;


@Service
public class UserService extends CommonService<User,String>{

	@Autowired
	private UserDao userDao;
	
	@Autowired
	public void setUserDao(UserDao userDao) {
		super.setCommonDao(userDao);
	}
	
	public User findUserByName(String userName){
		return this.userDao.findUserByName(userName);
	}
	
	public PageModel<User> queryForPage(Integer currentPage,Integer pageSize){
		return this.userDao.queryForPage(currentPage, pageSize);
	}

}
