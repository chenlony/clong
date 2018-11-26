package com.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TestController {
	@Autowired
	RestTemplate restTemplate;
	@Value("${userName}")
	private String userName;
	@RequestMapping("/getUserName")
	public String getUserName(){
		System.out.println(userName);
		return userName;
		
	}
}
