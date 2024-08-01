package com.springbootdemo.tickettrackerproj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springbootdemo.tickettrackerproj.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
