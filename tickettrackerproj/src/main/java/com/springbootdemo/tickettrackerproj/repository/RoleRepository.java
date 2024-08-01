package com.springbootdemo.tickettrackerproj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springbootdemo.tickettrackerproj.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
