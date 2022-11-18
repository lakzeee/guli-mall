package com.glm.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.glm.common.utils.PageUtils;
import com.glm.member.entity.MemberCollectSpuEntity;

import java.util.Map;

/**
 * 会员收藏的商品
 *
 * @author zehu
 * @email caizh1997@live.com
 * @date 2022-11-18 20:59:29
 */
public interface MemberCollectSpuService extends IService<MemberCollectSpuEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

