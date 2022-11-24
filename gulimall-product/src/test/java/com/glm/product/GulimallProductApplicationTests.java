package com.glm.product;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.glm.product.entity.BrandEntity;
import com.glm.product.service.BrandService;
import com.glm.product.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@Slf4j
@SpringBootTest
class GulimallProductApplicationTests {

	@Autowired
	BrandService brandService;

	@Autowired
	CategoryService categoryService;

	@Test
	void contextLoads() {
		List<BrandEntity> list = brandService.list(new QueryWrapper<BrandEntity>().eq("brand_id", "1"));
		list.forEach(System.out::println);
	}

	@Test
	void testFindCatelogPath(){
		Long[] catelogPath = categoryService.findCatelogPath(165L);
		log.info("path of catelog is {}", Arrays.asList(catelogPath));

	}

}
