package com.glm.product.service.impl;

import com.glm.product.service.CategoryBrandRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.glm.common.utils.PageUtils;
import com.glm.common.utils.Query;

import com.glm.product.dao.CategoryDao;
import com.glm.product.entity.CategoryEntity;
import com.glm.product.service.CategoryService;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Autowired
    CategoryBrandRelationService categoryBrandRelationService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> listWithTree() {
        // 1. Get all the category
        List<CategoryEntity> entities = baseMapper.selectList(null);
        // 2. Ensemble entities into catalog tree
        List<CategoryEntity> categoriesTree = entities
                .stream()
                .filter(categoryEntity->categoryEntity.getParentCid() == 0)
                .map((categoryEntity)->{
                    categoryEntity.setChildren(getChildren(categoryEntity, entities));
                    return categoryEntity;
                })
                .sorted((m1, m2)-> getSortHelper(m1) - getSortHelper(m2))
                .collect(Collectors.toList());
        return categoriesTree;
    }

    @Override
    public void removeCategoryByIds(List<Long> asList) {
        // TODO: check if category is used by somewhere
        baseMapper.deleteBatchIds(asList);
    }

    @Override
    public Long[] findCatelogPath(Long catelogId) {
        List<Long> paths = new ArrayList<>();
        List<Long> parentPath = findParentPath(catelogId, paths);
        Collections.reverse(parentPath);
        return (Long[]) paths.toArray(new Long[parentPath.size()]);
    }

    /**
     * Cascade update all the data
     * @param category
     */
    @Override
    public void updateCascade(CategoryEntity category) {
        this.updateById(category);
        categoryBrandRelationService.updateCategory(category.getCatId(), category.getName());

    }

    private List<Long> findParentPath(Long catelogId, List<Long> paths){
        paths.add(catelogId);
        CategoryEntity byId = this.getById(catelogId);
        if(byId.getParentCid()!=0){
            findParentPath(byId.getParentCid(), paths);
        }
        return paths;
    }

    private List<CategoryEntity> getChildren(CategoryEntity parentCategory, List<CategoryEntity> allEntities) {
        return allEntities
                .stream()
                .filter(categoryEntity -> categoryEntity.getParentCid() == parentCategory.getCatId())
                .map(categoryEntity -> {
                    categoryEntity.setChildren(getChildren(categoryEntity, allEntities));
                    return categoryEntity;
                })
                .sorted((m1, m2)-> getSortHelper(m1) - getSortHelper(m2))
                .collect(Collectors.toList());
    }
    private Integer getSortHelper(CategoryEntity categoryEntity){
        return categoryEntity.getSort() == null?0:categoryEntity.getSort();
    }



}