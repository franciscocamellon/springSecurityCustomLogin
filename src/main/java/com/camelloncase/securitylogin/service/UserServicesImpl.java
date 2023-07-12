package com.camelloncase.securitylogin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.camelloncase.securitylogin.dto.UserDto;
import com.camelloncase.securitylogin.model.User;
import com.camelloncase.securitylogin.repository.UserRepository;

@Service
public class UserServicesImpl implements UserService {
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	private UserRepository userRepository;

	public UserServicesImpl(UserRepository userRepository) {
		
		this.userRepository = userRepository;
	}

	@Override
	public User findByUsername(String username) {

		return userRepository.findByUsername(username);
	}

	@Override
	public User save(UserDto userDto) {
		User user = new User(userDto.getUsername(), passwordEncoder.encode(userDto.getPassword()), userDto.getFullname());
		return userRepository.save(user);
	}

}
