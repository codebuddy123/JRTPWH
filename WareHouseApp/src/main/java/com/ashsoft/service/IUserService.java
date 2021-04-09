package com.ashsoft.service;

import java.util.List;

import com.ashsoft.model.User;

public interface IUserService {
	
	public Integer saveUser(User user);
	
	public List<User> getAllUsers();
}
