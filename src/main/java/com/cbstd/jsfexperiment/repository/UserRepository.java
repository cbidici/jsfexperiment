package com.cbstd.jsfexperiment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cbstd.jsfexperiment.entity.User;

/**
 * Repository for User entity related database operations
 * 
 * @author cbidici
 *
 */
public interface UserRepository extends JpaRepository<User, Integer>{

	User findByUsername(String username);

}
