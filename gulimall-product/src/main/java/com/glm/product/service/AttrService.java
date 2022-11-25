package com.glm.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.glm.common.utils.PageUtils;
import com.glm.product.entity.AttrEntity;
import com.glm.product.vo.AttrGroupRelationVo;
import com.glm.product.vo.AttrRespVo;
import com.glm.product.vo.AttrVo;

import java.util.List;
import java.util.Map;

/**
 * 商品属性
 *
 * @author zehu
 * @email caizh1997@live.com
 * @date 2022-11-18 15:56:58
 */
public interface AttrService extends IService<AttrEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveAttrVo(AttrVo attr);

    PageUtils queryBaseAttrPage(Map<String, Object> params, Long catelogId, String type);

    AttrRespVo getAttrInfo(Long attrId);

    void updateAttr(AttrVo attr);

    List<AttrEntity> getRelationAttr(Long attrgroupId);

    void deleteRelation(AttrGroupRelationVo[] attrGroupRelationVos);

    PageUtils getNoRelationAttr(Map<String, Object> params, Long attrgroupId);
}

