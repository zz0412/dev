package com.luoshengsha.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luoshengsha.bean.User;
import com.luoshengsha.mapper.UserMapper;
import com.luoshengsha.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserMapper mapper;
	
	@Override
	public void save(User user) {
		//此处不再进行创建SqlSession和提交事务，都已交由spring去管理了。
		mapper.save(user);
	}

	@Override
	public void update(User user) {
		//此处不再进行创建SqlSession和提交事务，都已交由spring去管理了。
		mapper.update(user);
	}

	@Override
	public User find(String id) {
		//此处不再进行创建SqlSession，都已交由spring去管理了。
		return mapper.find(id);
	}

	@Override
	public void delete(String id) {
		//此处不再进行创建SqlSession和提交事务，都已交由spring去管理了。
		mapper.delete(id);
	}

}
