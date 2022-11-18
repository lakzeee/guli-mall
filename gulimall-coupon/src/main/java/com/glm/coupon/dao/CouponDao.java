package com.glm.coupon.dao;

import com.glm.coupon.entity.CouponEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券信息
 * 
 * @author zehu
 * @email caizh1997@live.com
 * @date 2022-11-18 21:02:54
 */
@Mapper
public interface CouponDao extends BaseMapper<CouponEntity> {
	
}
