package com.camelloncase.securitylogin.service;

import org.springframework.stereotype.Service;

import com.camelloncase.securitylogin.dto.UserDto;
import com.camelloncase.securitylogin.model.User;

@Service
public interface UserService {
	
	User findByUsername (String username);
	
	User save(UserDto userDto);

}
