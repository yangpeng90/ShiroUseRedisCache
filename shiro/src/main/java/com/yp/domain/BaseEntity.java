package com.yp.domain;

import java.io.Serializable;
import java.util.Date;

public class BaseEntity implements Serializable {

	private static final long serialVersionUID = -8583861751656993488L;
	
	protected String create_by;//创建者
	protected String create_dept;//创建的部门
	protected Date create_time;//创建的时间
	protected String update_by;//更新者
	protected Date update_time;//更新时间
	
	public String getCreate_by() {
		return create_by;
	}

	public void setCreate_by(String create_by) {
		this.create_by = create_by;
	}

	public String getCreate_dept() {
		return create_dept;
	}

	public void setCreate_dept(String create_dept) {
		this.create_dept = create_dept;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public String getUpdate_by() {
		return update_by;
	}

	public void setUpdate_by(String update_by) {
		this.update_by = update_by;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
}
