package com.glm.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.glm.common.utils.PageUtils;
import com.glm.coupon.entity.SeckillSkuNoticeEntity;

import java.util.Map;

/**
 * 秒杀商品通知订阅
 *
 * @author zehu
 * @email caizh1997@live.com
 * @date 2022-11-18 21:02:53
 */
public interface SeckillSkuNoticeService extends IService<SeckillSkuNoticeEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

