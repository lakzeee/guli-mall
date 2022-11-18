package com.glm.coupon.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.glm.coupon.entity.SmsHomeSubjectSpuEntity;
import com.glm.coupon.service.SmsHomeSubjectSpuService;
import com.glm.common.utils.PageUtils;
import com.glm.common.utils.R;



/**
 * 专题商品
 *
 * @author zehu
 * @email caizh1997@live.com
 * @date 2022-11-18 20:22:09
 */
@RestController
@RequestMapping("coupon/smshomesubjectspu")
public class SmsHomeSubjectSpuController {
    @Autowired
    private SmsHomeSubjectSpuService smsHomeSubjectSpuService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("coupon:smshomesubjectspu:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = smsHomeSubjectSpuService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("coupon:smshomesubjectspu:info")
    public R info(@PathVariable("id") Long id){
		SmsHomeSubjectSpuEntity smsHomeSubjectSpu = smsHomeSubjectSpuService.getById(id);

        return R.ok().put("smsHomeSubjectSpu", smsHomeSubjectSpu);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("coupon:smshomesubjectspu:save")
    public R save(@RequestBody SmsHomeSubjectSpuEntity smsHomeSubjectSpu){
		smsHomeSubjectSpuService.save(smsHomeSubjectSpu);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("coupon:smshomesubjectspu:update")
    public R update(@RequestBody SmsHomeSubjectSpuEntity smsHomeSubjectSpu){
		smsHomeSubjectSpuService.updateById(smsHomeSubjectSpu);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("coupon:smshomesubjectspu:delete")
    public R delete(@RequestBody Long[] ids){
		smsHomeSubjectSpuService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
