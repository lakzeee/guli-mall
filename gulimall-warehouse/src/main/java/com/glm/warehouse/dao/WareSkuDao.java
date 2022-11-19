package com.glm.warehouse.dao;

import com.glm.warehouse.entity.WareSkuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品库存
 * 
 * @author zehu
 * @email caizh1997@live.com
 * @date 2022-11-18 21:06:05
 */
@Mapper
public interface WareSkuDao extends BaseMapper<WareSkuEntity> {
	
}