package com.eureka.pojo;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableName;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Data
@TableName("stock")
public class Stock implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Integer Id;
    
    private String name;
    
    private Integer count;
    
    private Integer sale;
    
    private Integer version;
    
    private Date createTime;
 
}
