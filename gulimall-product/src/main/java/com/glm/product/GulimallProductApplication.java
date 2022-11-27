package com.glm.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "com.glm.product.feign")
@EnableDiscoveryClient
@MapperScan("com.glm.product.dao")
@SpringBootApplication
public class GulimallProductApplication {

    public static void main(String[] args) {
        System.setProperty("spring.cloud.bootstrap.enabled","true");
        SpringApplication.run(GulimallProductApplication.class, args);
    }

}
