package com.nucpoop.covserver.controller.api;

import java.net.URI;
import java.util.Collections;
import java.util.List;

import com.nucpoop.covserver.mapper.RoleMapper;
import com.nucpoop.covserver.model.ApiResponse;
import com.nucpoop.covserver.model.JwtAuthenticationResponse;
import com.nucpoop.covserver.model.LoginRequest;
import com.nucpoop.covserver.model.Role;
import com.nucpoop.covserver.model.RoleName;
import com.nucpoop.covserver.model.SignUpRequest;
import com.nucpoop.covserver.model.User;
import com.nucpoop.covserver.model.UserSummary;
import com.nucpoop.covserver.security.CurrentUser;
import com.nucpoop.covserver.security.JwtTokenProvider;
import com.nucpoop.covserver.security.UserPrincipal;
import com.nucpoop.covserver.service.UserService;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/user")
@MapperScan(basePackages = "com.nucpoop.covserver.dao")
public class UserController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	private UserService userService;

	@Autowired
	private RoleMapper roleMapper;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	JwtTokenProvider tokenProvider;

	@RequestMapping("/getUsers")
	public List<User> users() throws Exception {
		List<User> userList = userService.selectUsers();
		return userList;
	}

	@RequestMapping("/findbyid")
	public User userByID(@RequestParam(value = "id") int id) throws Exception {
		User user = userService.selectUserByIndex(id);
		return user;
	}

	@GetMapping("/me")
	public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
		UserSummary userSummary = new UserSummary(currentUser.getId(), currentUser.getUsername());
		return userSummary;
	}

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = tokenProvider.generateToken(authentication);
		return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
	}

	@RequestMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody SignUpRequest signUpRequest){
		
		// if (userService.existsByEmail(signUpRequest.getEmail())) {
		// 	return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"), HttpStatus.BAD_REQUEST);
		// }

		// Creating user's account
		User user = User.builder().email(signUpRequest.getEmail()).password(signUpRequest.getPassword()).build();


		user.setPassword(passwordEncoder.encode(user.getPassword()));

		Role userRole = roleMapper.findByName(RoleName.ROLE_USER);
				//.orElseThrow(() -> new AppException("User Role not set."));

		user.setRoles(Collections.singleton(userRole));

		try {
			userService.insertUser(user);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		//User result = userRepository.save(user);
		//User result = user;

		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/{email}")
				.buildAndExpand(user.getEmail()).toUri();

		return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
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
