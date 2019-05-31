package com.eureka;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;


@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.eureka.mapper")
@ComponentScan("com.eureka.*")
public class MmsApplication {
        public static void main(String[] args) {
            SpringApplication.run(MmsApplication.class, args);
        }
}
