package com.example.demo.login.domain.service.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.repository.mybatis.UserMapper;

@Service
public class ServiceMybatis {
	@Autowired
	private UserMapper userMapper;
		
	@Transactional
	public boolean insert(User user) {
//	insert実行
		return userMapper.insert(user);
	
	}
	@Transactional
	public User selectOne(String userId) {
//		select実行
		return userMapper.selectOne(userId); 
	}
	@Transactional
	public boolean update(User user ) {
//		update実行
		return userMapper.updateOne(user);
	}
	@Transactional
	public boolean delete(String userId) {
//		delete実行
		return userMapper.deleteOne(userId);
	}
	
}
