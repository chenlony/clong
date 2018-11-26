package com.eureka.mapper;

import org.apache.ibatis.annotations.Param;

import com.common.pojo.TbUser;

public interface TbUserMapper {
	Integer registerUser(TbUser user);
}
