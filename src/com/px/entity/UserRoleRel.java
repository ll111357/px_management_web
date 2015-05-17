package com.px.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.px.common.entity.BaseEntity;
/**
 * 用户角色关联
 * @author Jianfang Xu
 *
 */
@Entity
@Table(name = "org_user_role_rel")
public class UserRoleRel extends BaseEntity{

	private static final long serialVersionUID = -3488273525639258002L;

	private User user;
	
	private Role role;

	@ManyToOne
	@JoinColumn(name = "user_id")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne
	@JoinColumn(name = "role_id")
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	
}
