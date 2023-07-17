package com.camelloncase.securitylogin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.camelloncase.securitylogin.dto.UserDto;
import com.camelloncase.securitylogin.exception.UserAlreadyExistException;
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
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User save(UserDto userDto) throws UserAlreadyExistException {
		
		if (emailExists(userDto.getEmail())) {
			throw new UserAlreadyExistException("There is an account with that email address: " + userDto.getEmail());
		} else if (usernameExists(userDto.getUsername())) {
			throw new UserAlreadyExistException("There is an account with that username: " + userDto.getUsername());
		}
		
		final User user = new User();
		user.setUsername(userDto.getUsername());
		user.setEmail(userDto.getEmail());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		user.setFullname(userDto.getFullname());
		
		return userRepository.save(user);
	}
	
	public boolean emailExists(String email) {
		return userRepository.findByEmail(email) != null;
	}
	
	public boolean usernameExists(String username) {
		return userRepository.findByUsername(username) != null;
	}

}
