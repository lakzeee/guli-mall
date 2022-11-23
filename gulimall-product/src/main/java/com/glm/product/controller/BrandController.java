package com.glm.product.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.glm.common.valid.AddGroup;
import com.glm.common.valid.UpdateGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.glm.product.entity.BrandEntity;
import com.glm.product.service.BrandService;
import com.glm.common.utils.PageUtils;
import com.glm.common.utils.R;

import javax.validation.Valid;


/**
 * 品牌
 *
 * @author zehu
 * @email caizh1997@live.com
 * @date 2022-11-18 15:56:58
 */
@RestController
@RequestMapping("product/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("product:brand:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = brandService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{brandId}")
    //@RequiresPermissions("product:brand:info")
    public R info(@PathVariable("brandId") Long brandId){
		BrandEntity brand = brandService.getById(brandId);

        return R.ok().put("brand", brand);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("product:brand:save")
    public R save(@Validated(AddGroup.class) @RequestBody BrandEntity brand/*, BindingResult bindingResult*/){
//        if(bindingResult.hasErrors()){
//            Map<String,String> map = new HashMap<>();
//            bindingResult.getFieldErrors().forEach((error)->{
//                String message = error.getDefaultMessage();
//                String field = error.getField();
//                map.put(field, message);
//            });
//            return R.error(400, "Invalid Request").put("data", map);
//        }else {
//
//        }
        brandService.save(brand);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("product:brand:update")
    public R update(@Validated(UpdateGroup.class) @RequestBody BrandEntity brand){
		brandService.updateById(brand);

        return R.ok();
    }

    /**6 ;,./
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:brand:delete")
    public R delete(@RequestBody Long[] brandIds){
		brandService.removeByIds(Arrays.asList(brandIds));

        return R.ok();
    }

}
