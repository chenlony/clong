package com.eureka.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eureka.mapper.TbUserMapper;
import com.eureka.mapper1.TbUserMappero;
import com.eureka.pojo.TbUser;

@RestController
public class TestController {
	@Autowired
	private TbUserMapper tbUserMapper;
	@Autowired
	TbUserMappero tbUserMapper1;
	
	@RequestMapping("/getUser")
	public String getUserTest(){
		List<TbUser> selectList = tbUserMapper.getUser();
		System.out.println(selectList);
		List<TbUser> selectList2 = tbUserMapper1.getUser();
		System.out.println(selectList2);
		return null;
	}
}
