package com.vc.core.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vc.core.dao.UserDao;
import com.vc.core.model.User;
import com.vc.core.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	private static Logger logger = Logger.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDao userDao;

	@Override
	public String addUser(User User) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteUser(String userId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User getUser(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getUserLits(User User) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User login(String userLoginId, String userLoginPwd) {
		return userDao.login(userLoginId, userLoginPwd);
	}

	@Override
	public String updateUser(User User) {
		// TODO Auto-generated method stub
		return null;
	}

}
