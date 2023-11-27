package com.onlineshop.storage.core.processors.storage;

import com.onlineshop.storage.api.operations.storage.changeprice.ChangeStoragePriceInput;
import com.onlineshop.storage.api.operations.storage.changeprice.ChangeStoragePriceOperation;
import com.onlineshop.storage.api.operations.storage.changeprice.ChangeStoragePriceResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChangeStoragePriceCore implements ChangeStoragePriceOperation {
    @Override
    public ChangeStoragePriceResult process(ChangeStoragePriceInput input) {
        return null;
    }
}
