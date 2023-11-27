package com.onlineshop.storage.core.processors.sale;

import com.onlineshop.storage.api.operations.sales.get.byuser.GetSalesByUserInput;
import com.onlineshop.storage.api.operations.sales.get.byuser.GetSalesByUserOperation;
import com.onlineshop.storage.api.operations.sales.get.byuser.GetSalesByUserResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetSalesByUserCore implements GetSalesByUserOperation {
    @Override
    public GetSalesByUserResult process(GetSalesByUserInput input) {
        return null;
    }
}
