package com.px.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.px.common.dao.CommonDao;
import com.px.entity.Resource;

public interface ResourceDao extends CommonDao<Resource,String>{

	@Query("select r from Resource r where r.parent=null ")
	List<Resource> getRootResourceList();
	
}
