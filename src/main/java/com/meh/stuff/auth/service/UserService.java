package com.meh.stuff.auth.service;

import com.meh.stuff.auth.db.entity.User;
import com.meh.stuff.auth.db.exception.EntityNotFoundException;

public interface UserService {
    User findUserByUsername(String username) throws EntityNotFoundException;

    User saveUser(User user,
                  String username,
                  String salt) throws EntityNotFoundException;
}
