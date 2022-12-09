package com.glm.search.service;

import com.glm.common.to.es.SkuEsModel;

import java.io.IOException;
import java.util.List;

public interface ProductSaveSerive {
    boolean productStatusUp(List<SkuEsModel> skuEsModels) throws IOException;
}
