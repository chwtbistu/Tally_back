package com.bistu.tally.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bistu.tally.bean.ResultInfo;
import com.bistu.tally.dao.entity.User;
import com.bistu.tally.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping({ "/login/{username}&{password}" })
	public ResultInfo login(@PathVariable("username") String username, @PathVariable("password") String password) {
		log.info("get requesting...");
		ArrayList<User> users = new ArrayList<>();
		users = userService.findByUserNameAndPassword(username, password);
		if (users.size() != 0) {
			ResultInfo resultInfo = ResultInfo.success();
			log.info("users is: {}", users);
			resultInfo.setData(users);
			return resultInfo;
		} else {
			log.info("db do not have this username or password is wrong");
			ResultInfo resultInfo = ResultInfo.failure();
			return resultInfo;
		}
	}

	@GetMapping({ "/register/{username}&{password}" })
	public ResultInfo register(@PathVariable("username") String username, @PathVariable("password") String password) {
		log.info("get requesting...");
		if (userService.findByUserName(username).size() == 0) {
			if (userService.addUserFromUserNameAndPassword(username, password)) {
				ResultInfo resultInfo = ResultInfo.success();
				return resultInfo;
			} else {
				log.info("db can not add this username");
				ResultInfo resultInfo = ResultInfo.failure();
				return resultInfo;
			}
		} else {
			log.info("db has this username already");
			ResultInfo resultInfo = ResultInfo.failure();
			return resultInfo;
		}
	}
}
