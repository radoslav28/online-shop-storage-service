package com.onlineshop.storage.core.processors.sale;

import com.onlineshop.storage.api.operations.sales.get.byitem.GetSalesByItemInput;
import com.onlineshop.storage.api.operations.sales.get.byitem.GetSalesByItemOperation;
import com.onlineshop.storage.api.operations.sales.get.byitem.GetSalesByItemResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetSalesByItemCore implements GetSalesByItemOperation {
    @Override
    public GetSalesByItemResult process(GetSalesByItemInput input) {
        return null;
    }
}
