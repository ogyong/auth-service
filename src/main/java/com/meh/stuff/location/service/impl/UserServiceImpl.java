package com.meh.stuff.location.service.impl;

import org.springframework.stereotype.Service;

import com.meh.stuff.location.db.entity.User;
import com.meh.stuff.location.db.exception.EntityNotFoundException;
import com.meh.stuff.location.db.repository.UserRepository;
import com.meh.stuff.location.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private UserRepository userRepository;
	
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User findUserByUsername(String username) throws EntityNotFoundException {
		return userRepository.findUserByUsername(username).orElseThrow(() -> new EntityNotFoundException());
	}

}
