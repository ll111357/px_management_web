package com.px.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.px.common.entity.BaseEntity;

/**
 * 视频分类实体
 * @author pingan
 *
 */
@Entity
@Table(name = "video_type")
public class VideoType extends BaseEntity{

	private static final long serialVersionUID = -4776965944939912537L;
	
	private String name;//'分类名称'

	@Column(name = "name",length=64)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
