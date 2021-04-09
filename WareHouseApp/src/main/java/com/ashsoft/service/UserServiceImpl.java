package com.ashsoft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashsoft.model.User;
import com.ashsoft.repo.UserRepo;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepo repo;
	
	@Override
	public Integer saveUser(User user) {
		
		return repo.save(user).getUid();
	}

	@Override
	public List<User> getAllUsers() {
		
		return repo.findAll();
	}

}
