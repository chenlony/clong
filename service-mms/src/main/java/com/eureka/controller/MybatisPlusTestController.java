package com.eureka.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eureka.pojo.Stock;
import com.eureka.service.StockService;

@RestController
public class MybatisPlusTestController {
    @Autowired
    private StockService stockService;
    
    @GetMapping("/getAllStockList")
    public List<Stock> getStockList(){
        
        return stockService.getAllStockList();
    }
}
