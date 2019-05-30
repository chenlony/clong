package com.eureka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eureka.mapper.StockOrderMapper;
import com.eureka.pojo.Stock;
import com.eureka.pojo.StockOrder;

@Service
public class CreateSecKillService {
    
    @Autowired
    private StockService stockService;
    
    @Autowired
    private StockOrderMapper stockOrderMapper;
    
        public Integer createSecKill(Integer sid){ 
            
            //校验库存
            Stock stock = checkStock(sid);
            
            //扣库存
            saleStock(stock);
            
            //创建订单
            int id = createOrder(stock);
            
            return id;
        }
        
        private Stock checkStock(int sid) {
            Stock stock = stockService.getStockById(sid);
            if (stock.getSale().equals(stock.getCount())) {
                throw new RuntimeException("库存不足");
            }
            return stock;
        }
        
        private int saleStock(Stock stock) {
            stock.setSale(stock.getSale() + 1);
            return stockService.updateStockById(stock);
        }
        
        private int createOrder(Stock stock) {
            StockOrder order = new StockOrder();
            order.setSid(stock.getId());
            order.setName(stock.getName());
            int id = stockOrderMapper.insert(order);
            return id;
        }        
}
