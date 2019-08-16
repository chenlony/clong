package com.eureka.mapper;


import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eureka.pojo.TbUser;


public interface TbUserMapper extends BaseMapper<TbUser>{
	Integer registerUser(TbUser user);
	List<TbUser> getUser();
}
