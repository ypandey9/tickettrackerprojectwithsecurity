package com.springbootdemo.tickettrackerproj.service;

import java.util.List;

import com.springbootdemo.tickettrackerproj.dto.UserDto;
import com.springbootdemo.tickettrackerproj.entity.User;

public interface UserService {

	void saveUser(UserDto userDto);
	User findByEmail(String email);
	List<UserDto> findAllUsers();
}
