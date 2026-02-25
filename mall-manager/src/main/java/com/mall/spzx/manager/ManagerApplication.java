package com.mall.spzx.manager;

import com.mall.spzx.manager.properties.MinioProperties;
import com.mall.spzx.manager.properties.UserProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.util.DigestUtils;

@SpringBootApplication
@ComponentScan(basePackages = {"com.mall.spzx"})
@EnableConfigurationProperties({UserProperties.class, MinioProperties.class})
@EnableScheduling
public class ManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManagerApplication.class, args);
    }
}
