package com.vc.core.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vc.core.model.User;

@Service
public interface UserService {
	
	/**
	 * 根据用户ID，查询用户信息
	 * @param userId
	 * @return
	 */
	public User getUser(String userId);

	/**
	 * 查询用户列表
	 * @param User
	 * @return
	 */
	public List<User> getUserLits(User User);

	/**
	 * 新增用户
	 * @param User
	 * @return
	 */
	public String addUser(User User);

	/**
	 * 更新用户
	 * @param User
	 * @return
	 */
	public String updateUser(User User);

	/**
	 * 删除用户
	 * @param userId
	 * @return
	 */
	public boolean deleteUser(String userId);

	/**
	 * 用户登陆
	 * @param userId
	 * @param password
	 * @return
	 */
	public User login(String userLoginId, String userLoginPwd);

}
