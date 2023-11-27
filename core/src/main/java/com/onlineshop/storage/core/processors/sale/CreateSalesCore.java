package com.onlineshop.storage.core.processors.sale;

import com.onlineshop.storage.api.operations.sales.create.CreateSalesInput;
import com.onlineshop.storage.api.operations.sales.create.CreateSalesOperation;
import com.onlineshop.storage.api.operations.sales.create.CreateSalesResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateSalesCore implements CreateSalesOperation {
    @Override
    public CreateSalesResult process(CreateSalesInput input) {
        return null;
    }
}
