package com.nucpoop.covserver.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nucpoop.covserver.util.Utils;

@RestController
public class MainController {
	
	@RequestMapping("/")
	public String index() {
		return "user/test";
	}
	
	@RequestMapping("/test")
	public String test() throws IOException {
		return Utils.getCovData().toString();
	}

}
