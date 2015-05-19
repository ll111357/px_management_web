package com.px.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.px.common.entity.BaseEntity;

/**
 * 视频实体
 * @author pingan
 *
 */

@Entity
@Table(name = "video")
public class Video extends BaseEntity {

	private static final long serialVersionUID = -6767326487863022196L;
	
	private String name;//'名称'
	private String videoTypeId;//'视频分类Id'
	private String topCourseId;//'科目Id'
	private int duration;//'内容时长（分钟）'
	private String introduction;//'介绍'
	private String tag;//'标签'
	private String gradeId;//'年级Id'
	private String lecturerId;//'讲师Id'
	private String materialVersionId;//'教材版本Id'
	private String videoYunId;//'云端视频Id'
	private boolean isAudition;//是否支持试听（1-支持）
	private String sorting;//视频排序
	
	private VideoType videoType;//关联的视频类型实体
	
	@ManyToOne
	@JoinColumn(name="video_type_id",referencedColumnName = "id", insertable = false, updatable = false)
	public VideoType getVideoType() {
		return videoType;
	}
	public void setVideoType(VideoType videoType) {
		this.videoType = videoType;
	}
	
	
	@Column(name = "name",length=64)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "video_type_id",length=32)
	public String getVideoTypeId() {
		return videoTypeId;
	}
	public void setVideoTypeId(String videoTypeId) {
		this.videoTypeId = videoTypeId;
	}
	
	@Column(name = "top_course_id",length=32)
	public String getTopCourseId() {
		return topCourseId;
	}
	public void setTopCourseId(String topCourseId) {
		this.topCourseId = topCourseId;
	}
	
	@Column(name = "duration")
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	@Column(name = "introduction")
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	
	@Column(name = "tag",length=64)
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	@Column(name = "grade_id",length=32)
	public String getGradeId() {
		return gradeId;
	}
	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}
	
	@Column(name = "lecturer_id",length=32)
	public String getLecturerId() {
		return lecturerId;
	}
	public void setLecturerId(String lecturerId) {
		this.lecturerId = lecturerId;
	}
	
	@Column(name = "material_version_id",length=32)
	public String getMaterialVersionId() {
		return materialVersionId;
	}
	public void setMaterialVersionId(String materialVersionId) {
		this.materialVersionId = materialVersionId;
	}
	
	@Column(name = "video_yun_id",length=32)
	public String getVideoYunId() {
		return videoYunId;
	}
	public void setVideoYunId(String videoYunId) {
		this.videoYunId = videoYunId;
	}
	
	@Column(name = "is_audition")
	public boolean isAudition() {
		return isAudition;
	}
	public void setAudition(boolean isAudition) {
		this.isAudition = isAudition;
	}
	
	@Column(name = "sorting")
	public String getSorting() {
		return sorting;
	}
	public void setSorting(String sorting) {
		this.sorting = sorting;
	}
	
	
}
