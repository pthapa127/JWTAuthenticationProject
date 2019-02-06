package com.soft.app.JWTAuthenticationProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.soft.app.JWTAuthenticationProject.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	User findByUsername(String username);
}
