package com.fegin.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import com.fegin.error.MemberFeignImpl;


@FeignClient(value = "service-member",fallback = MemberFeignImpl.class)
public interface MemberFegin {
	@RequestMapping("/getUserList")
	public List<String> getOrderByUserList();
	
	@RequestMapping("/user/register")
	public String registerUser();
	
}
