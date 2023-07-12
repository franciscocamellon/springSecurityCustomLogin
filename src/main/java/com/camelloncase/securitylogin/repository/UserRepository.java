package com.camelloncase.securitylogin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.camelloncase.securitylogin.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByUsername (String username);

}
