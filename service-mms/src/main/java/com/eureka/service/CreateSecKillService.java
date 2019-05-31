package com.eureka.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eureka.mapper.StockOrderMapper;
import com.eureka.pojo.Stock;
import com.eureka.pojo.StockOrder;

@Service
public class CreateSecKillService {
    
    @Autowired
    private StockService stockService;
    
    @Autowired
    private StockOrderMapper stockOrderMapper;
        
    //@Transactional
        public Integer createSecKill(Integer sid){ 
            
            //校验库存
            Stock stock = checkStock(sid);
            
           /* //扣库存
            saleStock(stock);*/
            //乐观锁更新库存
            saleStockOptimistic(stock);
            
            //创建订单
            int id = createOrder(stock);
            
            return id;
        }
        
        private void saleStockOptimistic(Stock stock) {
            int count = stockService.updateStockByOptimistic(stock);
            if (count == 0){
                throw new RuntimeException("并发更新库存失败") ;
            }
            
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
            order.setCreateTime(new Date());
            int id = stockOrderMapper.insert(order);
            return id;
        }        
}
