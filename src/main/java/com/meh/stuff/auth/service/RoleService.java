package com.meh.stuff.auth.service;

import com.meh.stuff.auth.db.entity.Role;
import com.meh.stuff.auth.db.exception.EntityNotFoundException;

public interface RoleService {
    Role saveRole(Role role) throws EntityNotFoundException;
}
