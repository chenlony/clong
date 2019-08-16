package com.eureka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eureka.pojo.Stock;
import com.eureka.service.CreateSecKillService;
import com.eureka.service.StockService;
import com.sun.tools.classfile.StackMapTable_attribute.same_frame;

import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
public class CreateSecKillController {
    
    @Autowired
    private CreateSecKillService createSecKillService;
    
    @Autowired
    private StockService stockService;
    
    @RequestMapping("/createSecKill/{sid}")
    public String createSecKill(@PathVariable Integer sid){
        log.info("--------------");
//        logger.info("sid=[{}]", sid);
        int id = 0;
        try {
            id = createSecKillService.createSecKill(sid);
        } catch (Exception e) {
//            logger.error("Exception",e);
            log.error("异常", e);
        }
        return String.valueOf(id);
        
    }
    
    @GetMapping("/getStockById/{id}")
    public Stock getStockById(@PathVariable("id")Integer id){
        return stockService.getStockList(id);
    }
}
