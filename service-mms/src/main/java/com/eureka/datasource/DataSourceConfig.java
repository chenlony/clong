package com.eureka.datasource;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DataSourceConfig {
	
	@Bean
	@Primary
	@ConfigurationProperties(prefix="datasource1")
	public DataSource dbOneDataSource(){
		return DataSourceBuilder.create().build();
	}
	
	
	@Bean
	@Primary
	@ConfigurationProperties(prefix="datasource2")
	public DataSource dbTwoDataSource(){
		return DataSourceBuilder.create().build();
	}
}
