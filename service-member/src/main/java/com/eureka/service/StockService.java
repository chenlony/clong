package com.eureka.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.devtools.remote.server.HandlerMapper;
import org.springframework.stereotype.Service;


import com.eureka.mapper.StockMapper;
import com.eureka.pojo.Stock;

@Service
public class StockService {
    
    @Autowired
    private StockMapper stockMapper;

    public Stock getStockById(int sid) {
        Map<String,Object> map = new HashMap();
        map.put("id", sid);
//        Stock stock = (Stock) stockMapper.selectByMap(map);
        //Stock stock = stockMapper.selectById(sid);
        Stock stock2 = stockMapper.selectStockById(sid);
        return stock2;
    }

    public int updateStockById(Stock stock) {
        //int id = stockMapper.updateById(stock);
        return 0;
    }

    public int updateStockByOptimistic(Stock stock) {
      int i =  stockMapper.updateByOptimistic(stock);
        return i;
    }
    
}
