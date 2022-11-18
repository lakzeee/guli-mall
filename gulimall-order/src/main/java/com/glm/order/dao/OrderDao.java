package com.glm.order.dao;

import com.glm.order.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单
 * 
 * @author zehu
 * @email caizh1997@live.com
 * @date 2022-11-18 21:05:16
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {
	
}
