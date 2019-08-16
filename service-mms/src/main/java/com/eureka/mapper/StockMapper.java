package com.eureka.mapper;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.eureka.pojo.Stock;

//@Mapper
public interface StockMapper extends BaseMapper<Stock>{
    Stock selectStockById(@Param("sid")Integer sid);

    int updateByOptimistic(Stock stock);
    
}
