package com.px.dao;

import org.apache.commons.lang3.StringUtils;

import com.px.common.dao.CustomBaseSqlDaoImpl;
import com.px.common.entity.PageModel;
import com.px.entity.VideoType;

public class VideoTypeDaoImpl extends CustomBaseSqlDaoImpl implements VideoTypeDaoCustom {

	@Override
	public PageModel<VideoType> queryForPage(String id, String name,Integer currentPage,Integer pageSize) {
		String hql = "select l from VideoType l where 1=1 ";
		if(StringUtils.isNoneBlank(id)){
			hql+=" and l.id = '"+id+"' ";
		}
		if(StringUtils.isNotBlank(name)){
			hql+=" and l.name like '%"+name+"%' ";
		}
		hql+=" order by l.createDate desc ";
		return this.queryForPage(hql, currentPage, pageSize);
	}

	
}
