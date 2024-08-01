package com.springbootdemo.tickettrackerproj.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springbootdemo.tickettrackerproj.dto.UserDto;
import com.springbootdemo.tickettrackerproj.entity.Role;
import com.springbootdemo.tickettrackerproj.entity.User;
import com.springbootdemo.tickettrackerproj.repository.RoleRepository;
import com.springbootdemo.tickettrackerproj.repository.UserRepository;
@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;	
	
	
	
	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
			PasswordEncoder passwordEncoder) {
		
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public void saveUser(UserDto userDto) {
	
		User user=new User();
		user.setName(userDto.getFirstName()+" "+userDto.getLastName());
		user.setEmail(userDto.getEmail());
		
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		
		Role role=roleRepository.findByName("ROLE_USER");
		
		if(role==null) {
			role=checkRoleExist();
		}
		
		user.setRoles(Arrays.asList(role));
		userRepository.save(user);
	}

	private Role checkRoleExist() {
		Role role=new Role();
		role.setName("ROLE_USER");
		return roleRepository.save(role);
	}

	@Override
	public User findByEmail(String email) {
		
		return userRepository.findByEmail(email);
	}

	@Override
	public List<UserDto> findAllUsers() {
		List<User> users=userRepository.findAll();
		return users.stream().map((user)->convertEntityToDto(user))
				.collect(Collectors.toList());
	}

	private UserDto convertEntityToDto(User user) {
		UserDto userDto=new UserDto();
		String[] name=user.getName().split(" ");
		userDto.setFirstName(name[0]);
		userDto.setLastName(name[1]);
		userDto.setEmail(user.getEmail());
				
		return userDto;
	}

}
