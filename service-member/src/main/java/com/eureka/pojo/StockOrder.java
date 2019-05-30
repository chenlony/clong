package com.eureka.pojo;

import java.io.Serializable;
import java.util.Date;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Data
//@TableName("stock_order")
public class StockOrder implements Serializable{
   /**
     * 
     */
    private static final long serialVersionUID = 1L;

private Integer id;
   
   private Integer sid;
   
   private String name;
   
   private Date createTime;
}
