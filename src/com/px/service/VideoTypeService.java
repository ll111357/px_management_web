package com.px.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.px.common.entity.PageModel;
import com.px.common.service.CommonService;
import com.px.dao.VideoTypeDao;
import com.px.entity.VideoType;

@Service
public class VideoTypeService extends CommonService<VideoType,String>{

	@Autowired
	private VideoTypeDao videoTypeDao;

	@Autowired
	public void setVideoTypeDao(VideoTypeDao videoTypeDao) {
		super.setCommonDao(videoTypeDao);
	}

	public PageModel<VideoType> queryForPage(String account,String name,Integer currentPage,Integer pageSize){
		return this.videoTypeDao.queryForPage(account,name,currentPage, pageSize);
	}

}
