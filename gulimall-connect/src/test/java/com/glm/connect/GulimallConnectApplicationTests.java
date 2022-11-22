package com.glm.connect;

import com.aliyun.oss.OSSClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@SpringBootTest
class GulimallConnectApplicationTests {
    @Resource
    OSSClient ossClient;
    @Test
    public void te() throws FileNotFoundException {
        FileInputStream inputStream = new FileInputStream("/Users/lak/Downloads/oss-test-upload.png");
        ossClient.putObject("gulimall-lak","oss-test-upload4.jpg",inputStream);
        ossClient.shutdown();
        System.out.println("上传完成");
    }
}
