package com.meh.stuff.auth.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meh.stuff.auth.db.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}