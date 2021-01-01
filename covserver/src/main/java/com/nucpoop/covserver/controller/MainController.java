package com.nucpoop.covserver.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nucpoop.covserver.util.Utils;

@Controller
public class MainController {
	
	@RequestMapping("/")
	public String hello() {
		return "user/test";
	}
	
	@RequestMapping("/test")
	public @ResponseBody String test() throws IOException {
		return Utils.getCovData().toString();
	}

}
