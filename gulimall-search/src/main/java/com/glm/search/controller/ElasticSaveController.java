package com.glm.search.controller;

import com.glm.common.exception.ExceptionCodeEnum;
import com.glm.common.to.es.SkuEsModel;
import com.glm.common.utils.R;
import com.glm.search.service.ProductSaveSerive;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequestMapping("/search/save")
@RestController
public class ElasticSaveController {

    @Autowired
    ProductSaveSerive productSaveSerive;

    @PostMapping("product")
    public R productStatusUp(@RequestBody List<SkuEsModel> skuEsModels){
        boolean b = false;
        try {
            b = productSaveSerive.productStatusUp(skuEsModels);
        } catch (IOException e) {
            log.error("ElasticController changing production status to up failed: {}", e);
            return R.error(ExceptionCodeEnum.PRODUCT_UP_EXCEPTION.getCode(), ExceptionCodeEnum.PRODUCT_UP_EXCEPTION.getMsg());
        }
        if(!b){
            return R.ok();
        }else {
            return R.error(ExceptionCodeEnum.PRODUCT_UP_EXCEPTION.getCode(), ExceptionCodeEnum.PRODUCT_UP_EXCEPTION.getMsg());
        }
    }
}
