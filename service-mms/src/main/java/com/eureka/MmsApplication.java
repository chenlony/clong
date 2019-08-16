package com.eureka;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;


@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication(exclude=DataSourceAutoConfiguration.class)
//@MapperScan("com.eureka")
@ComponentScan("com.eureka.*")
public class MmsApplication {
        public static void main(String[] args) {
            SpringApplication.run(MmsApplication.class, args);
        }
}
