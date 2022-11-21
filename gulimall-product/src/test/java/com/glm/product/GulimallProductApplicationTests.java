package com.glm.product;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.glm.product.entity.BrandEntity;
import com.glm.product.service.BrandService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class GulimallProductApplicationTests {

	@Autowired
	BrandService brandService;

	@Test
	void contextLoads() {
//		BrandEntity brandEntity = new BrandEntity();
//		brandEntity.setBrandId(1L);
//		brandEntity.setDescript("apple");
//		brandEntity.setName("apple");
//		brandService.save(brandEntity);
//		System.out.println("saved");
		List<BrandEntity> list = brandService.list(new QueryWrapper<BrandEntity>().eq("brand_id", "1"));
		list.forEach(System.out::println);
	}
//	@Test
//	public void testAliCloudUpload() throws FileNotFoundException {
//		String endpoint = "oss-cn-shenzhen.aliyuncs.com";
//		String accessKeyId = "LTAI5tL8unc3acfnGKPmGYX9";
//		String accessKeySecret = "LhCPzYnbqb7bnUG0uw2adxPVWLCdvy";
//		String bucketName = "gulimall-lak";
//		String objectName = "oss-test-upload.png";
//		String filePath = "/Users/lak/Downloads/oss-test-upload.png";
//		OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
//		InputStream inputStream = new FileInputStream(filePath);
//		ossClient.putObject(bucketName, objectName, inputStream);
//		ossClient.shutdown();
//		System.out.println("Upload Success");
//}

}
