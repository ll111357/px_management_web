package com.px.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.px.common.entity.BaseEntity;

/**
 * 云视频资源实体
 * @author pingan
 *
 */

@Entity
@Table(name = "video_yun_data")
public class VideoYunData extends BaseEntity{

	private static final long serialVersionUID = 6785665281263408198L;
	
	private String name;// '名称',
	private String vid;// '视频Id',

	@Column(name = "name",length=32)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "vid",length=32)
	public String getVid() {
		return vid;
	}

	public void setVid(String vid) {
		this.vid = vid;
	}
	
	
}
