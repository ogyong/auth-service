package com.meh.stuff.location.service;

import com.meh.stuff.location.db.entity.User;
import com.meh.stuff.location.db.exception.EntityNotFoundException;

public interface UserService {
	User findUserByUsername(String username) throws EntityNotFoundException;
}
