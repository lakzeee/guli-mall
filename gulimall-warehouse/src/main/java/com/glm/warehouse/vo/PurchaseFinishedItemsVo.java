package com.glm.warehouse.vo;

import lombok.Data;

@Data
public class PurchaseFinishedItemsVo {
    private Long itemId;
    private Integer status;
    private String reason;
}
