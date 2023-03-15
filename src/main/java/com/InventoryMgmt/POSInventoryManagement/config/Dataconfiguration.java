package com.InventoryMgmt.POSInventoryManagement.config;

import com.InventoryMgmt.POSInventoryManagement.dao.JdbcUserDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@ComponentScan
public class Dataconfiguration {

    JdbcTemplate jdbcTemplate;
    @Bean
    public JdbcUserDao jdbcUserDao(){
        return new JdbcUserDao(jdbcTemplate);
    }
}
