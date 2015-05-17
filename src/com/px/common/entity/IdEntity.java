package com.px.common.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

/**
 * 统一定义id的entity基类.
 * 
 * 基类统一定义id的属性名称、数据类型、列名映射及生成策略.
 * Oracle需要每个Entity独立定义id的SEQUCENCE时，不继承于本类而改为实现一个Idable的接口。
 * 
 * @author
 */
// JPA 基类的标识
@MappedSuperclass
public abstract class IdEntity {
	
	protected String id;

	@Id
	@Column(length = 32, nullable = true)
	@GenericGenerator(name = "sys_uuid", strategy = "uuid")
	@GeneratedValue(generator = "sys_uuid")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

//	protected Long id;
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
	
}
