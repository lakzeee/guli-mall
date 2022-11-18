package com.glm.coupon.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.glm.coupon.entity.SmsCouponHistoryEntity;
import com.glm.coupon.service.SmsCouponHistoryService;
import com.glm.common.utils.PageUtils;
import com.glm.common.utils.R;



/**
 * 优惠券领取历史记录
 *
 * @author zehu
 * @email caizh1997@live.com
 * @date 2022-11-18 20:22:09
 */
@RestController
@RequestMapping("coupon/smscouponhistory")
public class SmsCouponHistoryController {
    @Autowired
    private SmsCouponHistoryService smsCouponHistoryService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("coupon:smscouponhistory:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = smsCouponHistoryService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("coupon:smscouponhistory:info")
    public R info(@PathVariable("id") Long id){
		SmsCouponHistoryEntity smsCouponHistory = smsCouponHistoryService.getById(id);

        return R.ok().put("smsCouponHistory", smsCouponHistory);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("coupon:smscouponhistory:save")
    public R save(@RequestBody SmsCouponHistoryEntity smsCouponHistory){
		smsCouponHistoryService.save(smsCouponHistory);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("coupon:smscouponhistory:update")
    public R update(@RequestBody SmsCouponHistoryEntity smsCouponHistory){
		smsCouponHistoryService.updateById(smsCouponHistory);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("coupon:smscouponhistory:delete")
    public R delete(@RequestBody Long[] ids){
		smsCouponHistoryService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
