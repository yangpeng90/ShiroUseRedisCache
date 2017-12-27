package com.yp.domain;

import java.util.HashSet;
import java.util.Set;

public class User extends BaseEntity {

	private static final long serialVersionUID = -4117474763681838123L;
	
	private String id;//用户id
	private String username;//用户名
	private String password;//密码
	private Integer state;//状态，0表示未启用，1表示启用
	
	private UserInfo userInfo;//一对一映射
	
	private Dept dept;//多对一关联
	
	private Set<Role> roles = new HashSet<Role>(0);
	
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}
}
