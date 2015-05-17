package com.px.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.px.common.service.CommonService;
import com.px.dao.ResourceDao;
import com.px.entity.Resource;

@Service
public class ResourceService extends CommonService<Resource,String>{

	@Autowired
	private ResourceDao resourceDao;

	@Autowired
	public void setResourceDao(ResourceDao resourceDao) {
		super.setCommonDao(resourceDao);
	}
	
	public List<Resource> getRootResourceList(){
		return resourceDao.getRootResourceList();
	}
	
}
