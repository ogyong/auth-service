package com.meh.stuff.auth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meh.stuff.auth.db.entity.Role;
import com.meh.stuff.auth.db.exception.EntityNotFoundException;
import com.meh.stuff.auth.db.repository.RoleRepository;
import com.meh.stuff.auth.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
    
    private RoleRepository roleRepository;
    
    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role saveRole(Role role) throws EntityNotFoundException {
        return roleRepository.save(role);
    }
}
