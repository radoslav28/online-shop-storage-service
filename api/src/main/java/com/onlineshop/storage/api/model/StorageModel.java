package com.onlineshop.storage.api.model;

import lombok.*;
import org.hibernate.validator.constraints.UUID;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StorageModel {

    @UUID
    private String id;

    @UUID
    private String itemId;

    private Long quantity;

    private BigDecimal price;
}
