package com.house;

import com.house.utils.JwtUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan("com.house.dao")
public class HouseApplication {
    public static void main(String[] args) throws UnknownHostException {
        Logger log = LoggerFactory.getLogger(HouseApplication.class);
        ConfigurableApplicationContext applicationContext = SpringApplication.run(HouseApplication.class, args);
        ConfigurableEnvironment env = applicationContext.getEnvironment();

    }

}
