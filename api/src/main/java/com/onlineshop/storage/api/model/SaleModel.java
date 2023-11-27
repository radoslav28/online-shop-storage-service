package com.onlineshop.storage.api.model;

import com.onlineshop.store.api.model.ItemModel;
import lombok.*;
import org.hibernate.validator.constraints.UUID;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaleModel {

    @UUID
    private String id;

    @UUID
    private String userId;

    private ItemModel itemModel;

    private Timestamp saleDate;

    private Long quantity;

    private BigDecimal price;
}
