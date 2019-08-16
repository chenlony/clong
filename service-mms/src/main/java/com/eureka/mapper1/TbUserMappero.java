package com.eureka.mapper1;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eureka.pojo.TbUser;

public interface TbUserMappero extends BaseMapper<TbUser>{
	Integer registerUser(TbUser user);
	List<TbUser> getUser();
}
