package com.onlineshop.storage.api.operations.storage.get.byitem;

import com.onlineshop.storage.api.base.ProcessorResult;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetStorageByItemResult implements ProcessorResult {

    private String id;

    private String itemId;

    private Long quantity;

    private BigDecimal price;
}
