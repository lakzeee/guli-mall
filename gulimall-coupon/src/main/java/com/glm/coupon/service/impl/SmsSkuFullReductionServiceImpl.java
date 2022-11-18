package com.glm.coupon.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.glm.common.utils.PageUtils;
import com.glm.common.utils.Query;

import com.glm.coupon.dao.SmsSkuFullReductionDao;
import com.glm.coupon.entity.SmsSkuFullReductionEntity;
import com.glm.coupon.service.SmsSkuFullReductionService;


@Service("smsSkuFullReductionService")
public class SmsSkuFullReductionServiceImpl extends ServiceImpl<SmsSkuFullReductionDao, SmsSkuFullReductionEntity> implements SmsSkuFullReductionService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SmsSkuFullReductionEntity> page = this.page(
                new Query<SmsSkuFullReductionEntity>().getPage(params),
                new QueryWrapper<SmsSkuFullReductionEntity>()
        );

        return new PageUtils(page);
    }

}