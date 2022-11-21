package com.glm.connect;

import com.aliyun.oss.OSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@EnableDiscoveryClient
@SpringBootApplication
public class GulimallConnectApplication {
    public static final String BUCKET_NAME = "gulimall-lak";
    public static void main(String[] args) {
        System.setProperty("spring.cloud.bootstrap.enabled","true");
        SpringApplication.run(GulimallConnectApplication.class, args);
    }

    @Bean
    public AppRunner appRunner() {
        return new AppRunner();
    }
    class AppRunner implements ApplicationRunner {

        @Autowired(required = false)
        private OSS ossClient;

        @Override
        public void run(ApplicationArguments args) throws Exception {
            try {
                if (!ossClient.doesBucketExist(BUCKET_NAME)) {
                    ossClient.createBucket(BUCKET_NAME);
                }
            } catch (Exception e) {
                System.err.println("oss handle bucket error: " + e.getMessage());
                System.exit(-1);
            }
        }
    }
}
