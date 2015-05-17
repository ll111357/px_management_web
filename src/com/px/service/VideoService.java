package com.px.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.px.common.entity.PageModel;
import com.px.common.service.CommonService;
import com.px.dao.VideoDao;
import com.px.entity.Video;

@Service
public class VideoService extends CommonService<Video,String>{
	@Autowired
	private VideoDao videoDao;

	@Autowired
	public void setVideoDao(VideoDao videoDao) {
		super.setCommonDao(videoDao);
	}
	
	public PageModel<Video> queryForPage(Video queryVideo,Integer currentPage,Integer pageSize){
		return this.videoDao.queryForPage(queryVideo,currentPage, pageSize);
	}
}
