package com.nucpoop.covserver.controller.api;

import java.net.URI;
import java.util.Collections;

import com.nucpoop.covserver.mapper.RoleMapper;
import com.nucpoop.covserver.model.ApiResponse;
import com.nucpoop.covserver.model.EmailRequest;
import com.nucpoop.covserver.model.JwtAuthenticationResponse;
import com.nucpoop.covserver.model.LocationRequest;
import com.nucpoop.covserver.model.LoginRequest;
import com.nucpoop.covserver.model.NotifyRequest;
import com.nucpoop.covserver.model.PasswordRequest;
import com.nucpoop.covserver.model.Role;
import com.nucpoop.covserver.model.RoleName;
import com.nucpoop.covserver.model.SignUpRequest;
import com.nucpoop.covserver.model.User;
import com.nucpoop.covserver.model.UserEmailCheck;
import com.nucpoop.covserver.model.UserSummary;
import com.nucpoop.covserver.model.UserUpdateRequest;
import com.nucpoop.covserver.security.CurrentUser;
import com.nucpoop.covserver.security.JwtTokenProvider;
import com.nucpoop.covserver.security.UserPrincipal;
import com.nucpoop.covserver.service.UserService;
import com.nucpoop.covserver.util.EmailUtilImpl;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
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

	private Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	EmailUtilImpl emailUtil;

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

	@GetMapping("/me")
	public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
		UserSummary userSummary = new UserSummary(currentUser.getId(), currentUser.getUsername(),
				currentUser.getNotifyYn(), currentUser.getLocation(), currentUser.getNotifyTime());
		return userSummary;
	}

	@GetMapping("/checkEmail")
	public UserEmailCheck getAvailabilityEmail(@RequestParam(value = "email") String email) {

		UserEmailCheck check = new UserEmailCheck(false);

		try {
			check = userService.checkEmail(email);
		} catch (Exception e) {
			System.out.println(e);
		}

		return check;
	}

	@DeleteMapping("/withdrawal")
	public ResponseEntity<?> withdrawalUser(@CurrentUser UserPrincipal currentUser,
			@RequestBody PasswordRequest passwordRequest) {
		//String password = passwordEncoder.encode(passwordRequest.getPassword());
		User user = User.builder().email(currentUser.getUsername()).password(passwordRequest.getPassword()).build();

		try {
			int result = userService.withdrawalUser(user);
			if (result == 1) {
				return ResponseEntity.ok(new ApiResponse(true, "정상적으로 탈퇴가 되었습니다."));
			} else {
				return ResponseEntity.ok(new ApiResponse(false, "비밀번호가 틀렸습니다."));
			}
		} catch (Exception e) {
			return ResponseEntity.ok(new ApiResponse(false, e.toString()));
		}
	}

	@PostMapping("/resetPassword")
	public ResponseEntity<?> resetPassword(@RequestBody EmailRequest emailRequest) {
		User user = User.builder().email(emailRequest.getEmail()).build();
		try {
			int password = userService.resetPassword(user);
			if (password != 0) {
				emailUtil.sendEmail(emailRequest.getEmail(), "Covid Notify 비밀번호 초기화", "초기화된 비밀번호 : " + password);

				return ResponseEntity.ok(new ApiResponse(true, "Success Password Reset"));
			} else {
				return ResponseEntity.ok(new ApiResponse(false, "Fail to reset"));
			}
		} catch (Exception e) {
			return ResponseEntity.ok(new ApiResponse(false, e.toString()));
		}
	}

	@PostMapping("/updatePassword")
	public ResponseEntity<?> updatePassword(@CurrentUser UserPrincipal currentUser,
			@RequestBody PasswordRequest passwordRequest) {

		if(passwordEncoder.matches(passwordRequest.getPassword(),currentUser.getPassword() )){
			String password = passwordEncoder.encode(passwordRequest.getNewPassword());
			User user = User.builder().password(password).email(currentUser.getUsername()).build();
	
			try {
				int result = userService.updatePassword(user);
				if (result == 1) {
					return ResponseEntity.ok(new ApiResponse(true, "Success Password Update"));
				} else {
					return ResponseEntity.ok(new ApiResponse(false, "Something Wrong"));
				}
			} catch (Exception e) {
				return ResponseEntity.ok(new ApiResponse(false, e.toString()));
			}
		}else{
			return ResponseEntity.ok(new ApiResponse(false, "비밀번호가 일치하지 않습니다."));
		}		
		
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
	public ResponseEntity<?> registerUser(@RequestBody SignUpRequest signUpRequest) {

		User user = User.builder().email(signUpRequest.getEmail()).password(signUpRequest.getPassword()).build();

		user.setPassword(passwordEncoder.encode(user.getPassword()));
		Role userRole = roleMapper.findByName(RoleName.ROLE_USER);
		user.setRoles(Collections.singleton(userRole));

		try {
			userService.insertUser(user);
		} catch (Exception e) {
			System.out.println(e);
		}

		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/{email}")
				.buildAndExpand(user.getEmail()).toUri();

		return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
	}

	@PostMapping("/updateLocation")
	public ResponseEntity<?> updateLocation(@CurrentUser UserPrincipal currentUser,
			@RequestBody LocationRequest locationRequest) {
		try {
			User user = User.builder().email(currentUser.getUsername()).location(locationRequest.getLocation()).build();
			int result = userService.updateLocation(user);

			if (result == 1) {
				return ResponseEntity.ok(new ApiResponse(true, "Success Location Update"));
			} else {
				return ResponseEntity.ok(new ApiResponse(false, "Fail to update location"));
			}

		} catch (Exception e) {
			return ResponseEntity.ok(new ApiResponse(false, e.toString()));
		}
	}

	@PostMapping("/updateNotify")
	public ResponseEntity<?> updateNotify(@CurrentUser UserPrincipal currentUser,@RequestBody NotifyRequest notifyRequest){
		try {
			User user = User.builder().email(currentUser.getUsername()).notifyYn(notifyRequest.getNotifyYn()).notifyTime(notifyRequest.getNotifyTime()).build();
			int result = userService.updateNotify(user);

			if (result == 1) {
				return ResponseEntity.ok(new ApiResponse(true, "Success Notify Update"));
			} else {
				return ResponseEntity.ok(new ApiResponse(false, "Fail to update notify"));
			}
		} catch (Exception e) {
			return ResponseEntity.ok(new ApiResponse(false, e.toString()));
		}
	}

	@PostMapping("/updateUser")
	public ResponseEntity<?> updateUser(@CurrentUser UserPrincipal currentUser,@ RequestBody UserUpdateRequest userUpdateRequest){
		try {
			User user = User.builder().password(userUpdateRequest.getPassword()).location(userUpdateRequest.getLocation()).email(currentUser.getUsername()).build();
			int result = userService.updateUserInfo(user);

			if (result == 1) {
				return ResponseEntity.ok(new ApiResponse(true, "Success user update"));
			} else {
				return ResponseEntity.ok(new ApiResponse(false, "Fail to update user"));
			}
		} catch (Exception e) {
			return ResponseEntity.ok(new ApiResponse(false, e.toString()));
		}
	}
}
