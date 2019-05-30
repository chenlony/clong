package com.eureka.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.regexp.recompile;
import org.junit.runners.Parameterized.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
//import com.codingapi.tx.annotation.TxTransaction;
import com.common.pojo.ResponseBase;
import com.common.utils.Constants;
import com.eureka.mapper.TbItemMapper;
import com.eureka.mapper.TbUserMapper;
import com.eureka.mq.RegisterMailboxProducer;
import com.eureka.pojo.TbItem;
import com.eureka.pojo.TbUser;




@RestController
public class MemberController {
	@Autowired
	private TbItemMapper itemMapper;
	@Autowired
	private TbUserMapper userMapper;
	@Autowired
	private RegisterMailboxProducer registerMailboxProducer;
	@Value("${messages.queue}")
	private String MESSAGESQUEUE;
	
	int count = 0;
	@RequestMapping("/getUserList")
//	@TxTransaction
	public List<String> getMemberAll(){
		ArrayList<String> listUser = new ArrayList<String>();
		listUser.add("zhangsan");
		listUser.add("lisi");
		listUser.add("wangwu");
		int i = count++;
		System.out.println(i);
		return listUser;
	}
	@RequestMapping("/getMemberServiceApi")
	public String getMemberServiceApi(){
		return "this is member";
	}
	private void sendMsg(String json) {
		ActiveMQQueue activeMQQueue = new ActiveMQQueue(MESSAGESQUEUE);
		registerMailboxProducer.sendMsg(activeMQQueue, json);

	}
	private String emailJson(String email) {
		JSONObject rootJson = new JSONObject();
		JSONObject header = new JSONObject();
		header.put("interfaceType", Constants.MSG_EMAIL);
		JSONObject content = new JSONObject();
		content.put("email", email);
		rootJson.put("header", header);
		rootJson.put("content", content);
		return rootJson.toJSONString();
	}
	@RequestMapping("/getItem/{itemId}")
	public TbItem getItem(@PathVariable Long itemId){ 
		System.out.println("itemId:"+itemId);
		 TbItem item = itemMapper.selectAllByKey(itemId);
		 return item;
	}
	
	@RequestMapping("/user/register")
	public String registerUser(TbUser user){
		user.setId(4999999L);
		user.setUserName("111");
		user.setCreated(new Date());
		user.setEmail("aaa@.com");
		user.setPassword("22222");
		user.setPhone("12333333333");
		user.setUpdated(new Date());
//		System.out.println(111111111);
		Integer regi = userMapper.registerUser(user);
//		int i = 1/0;
		System.out.println(regi);
		// 采用异步方式发送消息
//				String email = user.getEmail();
//				String json = emailJson(email);
////				log.info("####会员服务推送消息到消息服务平台####json:{}", json);
//				sendMsg(json);
		return "消息发送成功。";
		
	}
	
}
