package com.eureka.mapper;

import org.apache.ibatis.annotations.Param;

import com.eureka.pojo.TbItem;

public interface TbItemMapper {
  TbItem selectAllByKey(@Param("itemId")Long itemId);
}