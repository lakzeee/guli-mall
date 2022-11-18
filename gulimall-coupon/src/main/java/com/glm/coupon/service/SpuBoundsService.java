package com.glm.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.glm.common.utils.PageUtils;
import com.glm.coupon.entity.SpuBoundsEntity;

import java.util.Map;

/**
 * 商品spu积分设置
 *
 * @author zehu
 * @email caizh1997@live.com
 * @date 2022-11-18 21:02:53
 */
public interface SpuBoundsService extends IService<SpuBoundsEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

