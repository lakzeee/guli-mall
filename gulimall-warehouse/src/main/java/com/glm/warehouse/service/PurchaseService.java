package com.glm.warehouse.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.glm.common.utils.PageUtils;
import com.glm.warehouse.entity.PurchaseEntity;
import com.glm.warehouse.vo.MergeVo;

import java.util.Map;

/**
 * 采购信息
 *
 * @author zehu
 * @email caizh1997@live.com
 * @date 2022-11-18 21:06:05
 */
public interface PurchaseService extends IService<PurchaseEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryPageUnreceive(Map<String, Object> params);

    void mergePurchase(MergeVo mergeVo);
}

