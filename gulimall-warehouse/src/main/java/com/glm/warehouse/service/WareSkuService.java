package com.glm.warehouse.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.glm.common.utils.PageUtils;
import com.glm.warehouse.entity.WareSkuEntity;
import com.glm.warehouse.vo.SkuHasStockVo;

import java.util.List;
import java.util.Map;

/**
 * 商品库存
 *
 * @author zehu
 * @email caizh1997@live.com
 * @date 2022-11-18 21:06:05
 */
public interface WareSkuService extends IService<WareSkuEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void addStock(Long skuId, Long wareId, Integer skuNum);

    List<SkuHasStockVo> getSkuAvail(List<Long> skuIds);
}

