package com.px.dao;

import com.px.common.entity.PageModel;
import com.px.entity.VideoType;

public interface VideoTypeDaoCustom {

	PageModel<VideoType> queryForPage(String account,String name,Integer currentPage,Integer pageSize);
}
