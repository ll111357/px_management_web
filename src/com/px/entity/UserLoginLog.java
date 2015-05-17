package com.px.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.px.common.entity.BaseEntity;
/**
 * 系统用户登陆日志
 * @author Jeff Xu
 *
 */
@Entity
@Table(name = "user_login_log")
public class UserLoginLog extends BaseEntity{

	private static final long serialVersionUID = -953347925778162316L;
	
	public final static String EVENT_LOGIN = "login";//登陆

	public final static String EVENT_LOGIN_OUT = "login_out";//登出
	
	private User user;
	
	private String eventType;
	
	private String loginIp;

	@ManyToOne
	@JoinColumn(name="user_id")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name="login_ip",length=15)
	public String getLoginIp() {
		return loginIp;
	}
	
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	@Column(name="event_type",length=10)
	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	

}
