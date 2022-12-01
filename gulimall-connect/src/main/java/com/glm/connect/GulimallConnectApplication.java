package com.glm.connect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.commons.util.UtilAutoConfiguration;

@EnableDiscoveryClient
@SpringBootApplication(exclude = {UtilAutoConfiguration.class})
public class GulimallConnectApplication {
    public static void main(String[] args) {
        System.setProperty("spring.cloud.bootstrap.enabled","true");
        SpringApplication.run(GulimallConnectApplication.class, args);
    }
}
