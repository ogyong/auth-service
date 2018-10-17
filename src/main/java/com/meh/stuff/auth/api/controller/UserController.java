package com.meh.stuff.auth.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meh.stuff.auth.api.dto.UserDto;
import com.meh.stuff.auth.db.entity.User;
import com.meh.stuff.auth.db.exception.EntityNotFoundException;
import com.meh.stuff.auth.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	private UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/{username}")
	public UserDto findUserByUsername(@PathVariable(name = "username") String username) throws EntityNotFoundException {
		User user = userService.findUserByUsername(username);
		return UserDto.of(user);
	}
}
