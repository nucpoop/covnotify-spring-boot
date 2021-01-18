package com.nucpoop.covserver.controller;

import java.io.IOException;
import java.util.List;

import org.json.JSONObject;
import org.json.XML;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nucpoop.covserver.dao.UserDao;
import com.nucpoop.covserver.model.User;
import com.nucpoop.covserver.service.UserService;
import com.nucpoop.covserver.util.Utils;

@RestController
@MapperScan(basePackages = "com.nucpoop.covserver.dao")
public class MainController {

	@Autowired
	private UserService userService;
	//private UserDao userDao;

	@RequestMapping("/")
	public String hello() {
		return "user/test";
	}

	@RequestMapping("/test")
	public @ResponseBody String test() throws IOException {
		JSONObject jsonObject = XML.toJSONObject(Utils.getCovData().toString());

		return jsonObject.toString();
	}

	@RequestMapping("/user")
	public List<User> users() throws Exception {
		List<User> userList = userService.selectUsers();
		return userList;
	}

	@RequestMapping("/userbyid")
	public User userByID(@RequestParam(value = "id") int id) throws Exception {
		User user = userService.selectUserByID(id);
		return user;
	}

	@RequestMapping("/user_insert")
	public int userInsert(@RequestParam(value = "userID") String id, @RequestParam(value = "userPasswd") String passwd,
			@RequestParam(value = "userName") String name, @RequestParam(value = "userPhone") String phone,
			@RequestParam(value = "userEmail") String email) throws Exception {
		int result;		
		User user = User.builder()
		.userID(id).userPasswd(passwd).userName(name).userPhone(phone).userEmail(email)
				.build();
		result = userService.insertUser(user);
		return result;
	}

	
}
