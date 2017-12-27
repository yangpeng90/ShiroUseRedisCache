package com.yp.domain;

import java.util.HashSet;
import java.util.Set;

public class Role extends BaseEntity {

	private static final long serialVersionUID = -4297963744798356462L;

	private String id;
	private String name;
	private String remark;
	private Integer orderNo;
	
	private Set<User> users = new HashSet<User>(0);
	
	private Set<Module> modules = new HashSet<Module>(0);
	
	public Set<Module> getModules() {
		return modules;
	}
	public void setModules(Set<Module> modules) {
		this.modules = modules;
	}
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", remark=" + remark
				+ ", orderNo=" + orderNo + "]";
	}
	
}
