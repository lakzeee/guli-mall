package com.glm.warehouse.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class PurchaseFinishedVo {
    @NotNull
    private Long id;

    private List<PurchaseFinishedItemsVo> items;
}
