package com.yp.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.yp.domain.Page;
import com.yp.domain.User;

public class UserServiceImpl implements UserService {

	@Override
	public List<User> find(String hql, Class<User> entityClass,
			Object... params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User get(Class<User> entityClass, Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<User> findPage(String hql, Page<User> page, Object... params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdate(User entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveOrUpdateAll(Collection<User> entitys) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Class<User> entityClass, Serializable id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Class<User> entityClass, Serializable[] ids) {
		// TODO Auto-generated method stub
		
	}


}
