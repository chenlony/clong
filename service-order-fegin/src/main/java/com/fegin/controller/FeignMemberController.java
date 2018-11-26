package com.fegin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codingapi.tx.annotation.TxTransaction;
import com.fegin.service.MemberFegin;

@RestController
public class FeignMemberController {
	@Autowired
	private MemberFegin memberFeign;
	
	@RequestMapping("/getFeignOrderFeign")
	@TxTransaction//事物处理
	public List<String> getFeignOrderFeign(){
		return memberFeign.getOrderByUserList();
	}
	
	@RequestMapping("/getOrderFeign")
	public String getOrderFeign(){
		return "getOrderFeign";
	}
	@TxTransaction
	@RequestMapping("/register")
	public String registerUser(){
		String string = memberFeign.registerUser();
		System.out.println("111;"+string);
		return string;
		
	}
	
}
