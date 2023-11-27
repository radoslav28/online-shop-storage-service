package com.onlineshop.storage.api.operations.sales.get.byuser;

import com.onlineshop.storage.api.base.ProcessorResult;
import com.onlineshop.storage.api.model.SaleModel;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetSalesByUserResult implements ProcessorResult {
    private List<SaleModel> sales;
}
