package com.springbootdemo.tickettrackerproj.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UtilClass {

	public static void main(String[] args) {
		
		PasswordEncoder encoder=new BCryptPasswordEncoder();
		System.out.println(encoder.encode("yogesh"));
		System.out.println(encoder.encode("atul"));

	}

}
