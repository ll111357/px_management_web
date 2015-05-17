package com.px.dao;

import com.px.common.entity.PageModel;
import com.px.entity.Video;
import com.px.entity.VideoYunData;

public interface VideoDaoCustom {

	PageModel<Video> queryForPage(Video queryVideo,Integer currentPage,Integer pageSize);
	
	PageModel<VideoYunData> queryVideoYunPage(VideoYunData queryVideoYun,Integer currentPage,Integer pageSize);
}
