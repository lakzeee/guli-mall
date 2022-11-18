package com.glm.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.glm.common.utils.PageUtils;
import com.glm.coupon.entity.CouponSpuRelationEntity;

import java.util.Map;

/**
 * 优惠券与产品关联
 *
 * @author zehu
 * @email caizh1997@live.com
 * @date 2022-11-18 21:02:53
 */
public interface CouponSpuRelationService extends IService<CouponSpuRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

