package com.meh.stuff.auth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meh.stuff.auth.db.entity.Permission;
import com.meh.stuff.auth.db.exception.EntityNotFoundException;
import com.meh.stuff.auth.db.repository.PermissionRepository;
import com.meh.stuff.auth.service.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService {
    
    private PermissionRepository permissionRepository;
    
    @Autowired
    public PermissionServiceImpl(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Override
    public Permission savePermission(Permission permission) throws EntityNotFoundException {
        return permissionRepository.save(permission);
    }

}
