package com.bistu.tally.dao.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bistu.tally.dao.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	ArrayList<User> findByUserNameAndUserPassword(String userName, String password);

	ArrayList<User> findByUserName(String userName);

}
