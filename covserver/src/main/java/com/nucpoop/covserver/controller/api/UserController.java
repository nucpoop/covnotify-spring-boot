package com.nucpoop.covserver.controller.api;

import java.io.IOException;
import java.util.List;

import com.nucpoop.covserver.model.SearchCondition;
import com.nucpoop.covserver.model.User;
import com.nucpoop.covserver.service.UserService;
import com.nucpoop.covserver.util.Utils;

import org.json.JSONObject;
import org.json.XML;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@MapperScan(basePackages = "com.nucpoop.covserver.dao")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("/cov")
	public ResponseEntity<String> cov(@RequestParam(value = "date") String date) throws IOException {
		SearchCondition searchCondition = SearchCondition.builder().pageNo(1).numberOfRows(1).startCreateDt(date).endCreateDt(date).build();
		JSONObject jsonObject = XML.toJSONObject(Utils.getCovData(searchCondition).toString());

		return ResponseEntity.ok(jsonObject.toString());
	}

	@RequestMapping("/covLocal")
	public @ResponseBody String covLocal(@RequestParam(value = "date") String date) throws IOException{
		SearchCondition condition = SearchCondition.builder().pageNo(1).numberOfRows(1).startCreateDt(date).endCreateDt(date).build();
		JSONObject jsonObject = XML.toJSONObject(Utils.getCovDataLocal(condition).toString());
		return jsonObject.toString();
	}

	@RequestMapping("/user")
	public List<User> users() throws Exception {
		List<User> userList = userService.selectUsers();
		return userList;
	}

	@RequestMapping("/userbyid")
	public User userByID(@RequestParam(value = "id") int id) throws Exception {
		User user = userService.selectUserByIndex(id);
		return user;
	}

	// @RequestMapping("/user_insert")
	// public int userInsert(@RequestParam(value = "userID") String id, @RequestParam(value = "userPasswd") String passwd,
	// 		@RequestParam(value = "userName") String name, @RequestParam(value = "userPhone") String phone,
	// 		@RequestParam(value = "userEmail") String email) throws Exception {
	// 	int result;		
	// 	User user = User.builder()
	// 	.userID(id).userPasswd(passwd).userName(name).userPhone(phone).userEmail(email)
	// 			.build();
	// 	result = userService.insertUser(user);
	// 	return result;
	// }
}
