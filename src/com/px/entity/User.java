package com.px.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.px.common.entity.BaseEntity;
/**
 * 系统用户
 * @author Jeff Xu
 *
 */
@Entity
@Table(name = "org_user")
public class User extends BaseEntity{

	private static final long serialVersionUID = -8821121831372299051L;
	
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 密码
	 */
	private String password;
	
	/**
	 * 真实姓名
	 */
	private String realName;

	@Column(name = "username",length=50)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password",length=32)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "real_name",length=50)
	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}
	
	

}
