package com.eureka.mapper;

import org.apache.ibatis.annotations.Param;

import com.eureka.pojo.Stock;

//@Mapper
public interface StockMapper {
    Stock selectStockById(@Param("sid")Integer sid);

    int updateByOptimistic(Stock stock);
}
