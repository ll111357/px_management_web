package com.px.dto;

import java.util.List;

public class ResourceDTO {
	
	private String id;

	private String name;
	
	private String url;
	
	private String type;
	
	private String target = "rightFrame";// 打开的目标
	
	private List<ResourceDTO> children;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public List<ResourceDTO> getChildren() {
		return children;
	}

	public void setChildren(List<ResourceDTO> children) {
		this.children = children;
	}
	
}
