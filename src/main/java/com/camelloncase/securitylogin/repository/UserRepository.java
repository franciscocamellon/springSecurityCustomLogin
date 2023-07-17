package com.camelloncase.securitylogin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.camelloncase.securitylogin.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByUsername (String username);
	
	User findByEmail(String email);

}
