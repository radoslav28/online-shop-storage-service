package com.onlineshop.storage.api.operations.sales.create;

import com.onlineshop.storage.api.base.ProcessorResult;
import com.onlineshop.storage.api.model.SaleModel;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateSalesResult implements ProcessorResult {
    private List<SaleModel> sales;
}
