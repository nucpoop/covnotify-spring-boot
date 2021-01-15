package com.nucpoop.covserver.controller;

import java.io.IOException;
import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nucpoop.covserver.dao.UserDao;
import com.nucpoop.covserver.model.User;
import com.nucpoop.covserver.util.Utils;

@RestController
@MapperScan(basePackages = "com.nucpoop.covserver.dao")
public class MainController {
	@Autowired
	private UserDao userDao;
	
	@RequestMapping("/")
	public String hello() {
		return "user/test";
	}
	
	@RequestMapping("/test")
	public @ResponseBody String test() throws IOException {

		return Utils.getCovData().toString();
	}

	@RequestMapping("/user")
	public List<User> users() throws Exception{
		List<User> userList = userDao.selectUsers();
		return userList;
	}
}
