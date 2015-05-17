package com.px.dao;

import org.apache.commons.lang3.StringUtils;

import com.px.common.dao.CustomBaseSqlDaoImpl;
import com.px.common.entity.PageModel;
import com.px.entity.Video;
import com.px.entity.VideoYunData;

public class VideoDaoImpl extends CustomBaseSqlDaoImpl implements VideoDaoCustom{

	@Override
	public PageModel<Video> queryForPage(Video queryVideo, Integer currentPage,
			Integer pageSize) {
		String hql = "select l from Video l where 1=1 ";
		if(StringUtils.isNoneBlank(queryVideo.getId())){
			hql+=" and l.id = '"+queryVideo.getId()+"' ";
		}
		if(StringUtils.isNotBlank(queryVideo.getName())){
			hql+=" and l.name like '%"+queryVideo.getName()+"%' ";
		}
		if(StringUtils.isNotBlank(queryVideo.getVideoYunId())){
			hql+=" and l.videoYunId = '"+queryVideo.getVideoYunId()+"' ";
		}
		if(StringUtils.isNotBlank(queryVideo.getDeleteFlag())){
			hql+=" and l.deleteFlag = '"+queryVideo.getDeleteFlag()+"' ";
		}
		if(StringUtils.isNotBlank(queryVideo.getVideoTypeId())){
			hql+=" and l.videoTypeId like '"+queryVideo.getVideoTypeId()+"' ";
		}
		if(StringUtils.isNotBlank(queryVideo.getName())){
			hql+=" and l.name like '%"+queryVideo.getName()+"%' ";
		}
		hql+=" order by l.createDate desc ";
		return this.queryForPage(hql, currentPage, pageSize);
	}

	@Override
	public PageModel<VideoYunData> queryVideoYunPage(
			VideoYunData queryVideoYun, Integer currentPage, Integer pageSize) {
		
		String hql = "select l from VideoYunData l where 1=1 ";
		if(StringUtils.isNotBlank(queryVideoYun.getName())){
			hql+=" and l.name like '%"+queryVideoYun.getName()+"%' ";
		}
		if(StringUtils.isNotBlank(queryVideoYun.getVid())){
			hql+=" and l.vid = '"+queryVideoYun.getVid()+"' ";
		}
		hql+=" order by l.createDate desc ";
		return this.queryForPage(hql, currentPage, pageSize);
	}
}
