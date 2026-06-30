package com.cs.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cs.demo.entity.Users;

public interface UserRepo extends JpaRepository<Users, Long> {

	boolean existsByMobile(String mobile);
	
	Users findByMobile(String mobile);
	
	
}
