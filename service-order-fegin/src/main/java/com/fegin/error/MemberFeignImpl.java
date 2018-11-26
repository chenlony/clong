package com.fegin.error;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fegin.service.MemberFegin;
@Component
public class MemberFeignImpl implements MemberFegin {

	@Override
	public List<String> getOrderByUserList() {
		List<String> list = new ArrayList<>();
		list.add("not order list");
		return list;
	}

	@Override
	public String registerUser() {
		
		return "注册失败";
	}

}
