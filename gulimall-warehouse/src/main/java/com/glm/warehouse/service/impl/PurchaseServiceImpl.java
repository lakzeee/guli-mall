package com.glm.warehouse.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.glm.common.constant.WarehouseConst;
import com.glm.warehouse.dao.PurchaseDetailDao;
import com.glm.warehouse.entity.PurchaseDetailEntity;
import com.glm.warehouse.service.PurchaseDetailService;
import com.glm.warehouse.service.WareSkuService;
import com.glm.warehouse.vo.MergeVo;
import com.glm.warehouse.vo.PurchaseFinishedItemsVo;
import com.glm.warehouse.vo.PurchaseFinishedVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.glm.common.utils.PageUtils;
import com.glm.common.utils.Query;

import com.glm.warehouse.dao.PurchaseDao;
import com.glm.warehouse.entity.PurchaseEntity;
import com.glm.warehouse.service.PurchaseService;
import org.springframework.transaction.annotation.Transactional;


@Service("purchaseService")
public class PurchaseServiceImpl extends ServiceImpl<PurchaseDao, PurchaseEntity> implements PurchaseService {

    @Autowired
    PurchaseDetailService purchaseDetailService;

    @Autowired
    PurchaseDetailDao purchaseDetailDao;

    @Autowired
    WareSkuService wareSkuService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PurchaseEntity> page = this.page(
                new Query<PurchaseEntity>().getPage(params),
                new QueryWrapper<PurchaseEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPageUnreceive(Map<String, Object> params) {

        IPage<PurchaseEntity> page = this.page(
                new Query<PurchaseEntity>().getPage(params),
                new QueryWrapper<PurchaseEntity>().eq("status", 0).or().eq("status", 1)
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional
    public void mergePurchase(MergeVo mergeVo) {
        Long purchaseId = mergeVo.getPurchaseId();
        if(purchaseId == null){
            PurchaseEntity purchaseEntity = new PurchaseEntity();
            purchaseEntity.setStatus(WarehouseConst.PurchaseStatusEnum.CREATED.getCode());
            purchaseEntity.setCreateTime(new Date());
            purchaseEntity.setUpdateTime(new Date());
            this.save(purchaseEntity);
            purchaseId = purchaseEntity.getId();
        }
        List<Long> items = mergeVo.getItems();
        Long finalPurchaseId = purchaseId;
        List<PurchaseDetailEntity> collect = items.stream().map(item -> {
            PurchaseDetailEntity detailEntity = new PurchaseDetailEntity();
            detailEntity.setId(item);
            detailEntity.setPurchaseId(finalPurchaseId);
            detailEntity.setStatus(WarehouseConst.PurchaseDetailStatusEnum.ASSIGNED.getCode());
            return detailEntity;
        }).collect(Collectors.toList());
        purchaseDetailService.updateBatchById(collect);

        PurchaseEntity purchaseEntity = new PurchaseEntity();
        purchaseEntity.setId(purchaseId);
        purchaseEntity.setUpdateTime(new Date());
        this.updateById(purchaseEntity);
    }

    @Override
    public void receivedPurchaseOrder(List<Long> ids) {
        List<PurchaseEntity> collect = ids.stream().map(id -> {
            PurchaseEntity byId = this.getById(id);
            return byId;
        }).filter(item -> {
            if (item.getStatus() == WarehouseConst.PurchaseDetailStatusEnum.CREATED.getCode() ||
                    item.getStatus() == WarehouseConst.PurchaseDetailStatusEnum.ASSIGNED.getCode()){
                return true;
            }
            return false;
        }).map(item->{
            item.setStatus(WarehouseConst.PurchaseStatusEnum.RECEIVED.getCode());
            return item;
        }).collect(Collectors.toList());

        this.updateBatchById(collect);

        collect.forEach(item->{
            List<PurchaseDetailEntity> entities = purchaseDetailService.listDetailByPurchaseId(item.getId());
            List<PurchaseDetailEntity> collect1 = entities.stream().map(entity -> {
                PurchaseDetailEntity detailEntity = new PurchaseDetailEntity();
                detailEntity.setId(entity.getId());
                detailEntity.setStatus(WarehouseConst.PurchaseDetailStatusEnum.PURCHASING.getCode());
                return detailEntity;
            }).collect(Collectors.toList());

            purchaseDetailService.updateBatchById(collect1);
        });
    }

    @Transactional
    @Override
    public void done(PurchaseFinishedVo doneVo) {
        Long id = doneVo.getId();

        Boolean flag = true;
        List<PurchaseFinishedItemsVo> items = doneVo.getItems();

        ArrayList<PurchaseDetailEntity> updates = new ArrayList<>();

        if(items != null && items.size() > 0){
            for (PurchaseFinishedItemsVo item : items) {
                LambdaQueryWrapper<PurchaseDetailEntity> wrapper = new LambdaQueryWrapper<PurchaseDetailEntity>();
                PurchaseDetailEntity entity = purchaseDetailDao.selectOne(wrapper.and(w -> {
                    w.eq(PurchaseDetailEntity::getPurchaseId, id).eq(PurchaseDetailEntity::getSkuId, item.getItemId());
                }));
                if(item.getStatus() == WarehouseConst.PurchaseDetailStatusEnum.HASERROR.getCode()) {
                    flag = false;
                    entity.setStatus(item.getStatus());
                }else {
                    entity.setStatus(WarehouseConst.PurchaseDetailStatusEnum.FINISHED.getCode());
                    wareSkuService.addStock(entity.getSkuId(), entity.getWareId(), entity.getSkuNum());
                }
                updates.add(entity);
            }
            purchaseDetailService.updateBatchById(updates);
        }

        PurchaseEntity purchaseEntity = new PurchaseEntity();
        purchaseEntity.setId(id);
        purchaseEntity.setStatus(flag?WarehouseConst.PurchaseStatusEnum.FINISHED.getCode() : WarehouseConst.PurchaseStatusEnum.HASERROR.getCode());
        purchaseEntity.setUpdateTime(new Date());
        this.updateById(purchaseEntity);

    }
}