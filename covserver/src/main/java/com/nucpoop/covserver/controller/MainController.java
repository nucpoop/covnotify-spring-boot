package com.nucpoop.covserver.controller;

import com.nucpoop.covserver.util.Utils;

import java.io.IOException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
	
	@RequestMapping(value ="/home")
	public String home() {
		return "index.html";
	}
	
	@RequestMapping("/test")
	public String test() throws IOException {
		return Utils.getCovData().toString();
	}

}
