package com.meh.stuff.auth.service;

import com.meh.stuff.auth.db.entity.Permission;
import com.meh.stuff.auth.db.exception.EntityNotFoundException;

public interface PermissionService {
    Permission savePermission(Permission permission) throws EntityNotFoundException;
}
