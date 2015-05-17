package com.px.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.px.common.entity.BaseEntity;

/**
 * 角色资源关联
 * @author Jianfang Xu
 *
 */
@Entity
@Table(name = "org_role_resource_rel")
public class RoleResourceRel  extends BaseEntity{

	private static final long serialVersionUID = 948060977883216623L;
	
	private Role role;
	
	private Resource resource;

	@ManyToOne
	@JoinColumn(name="role_id")
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@ManyToOne
	@JoinColumn(name="resource_id")
	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

}
