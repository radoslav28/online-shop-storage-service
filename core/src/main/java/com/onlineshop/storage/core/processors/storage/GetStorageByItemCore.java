package com.onlineshop.storage.core.processors.storage;

import com.onlineshop.storage.api.operations.storage.get.byitem.GetStorageByItemInput;
import com.onlineshop.storage.api.operations.storage.get.byitem.GetStorageByItemOperation;
import com.onlineshop.storage.api.operations.storage.get.byitem.GetStorageByItemResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetStorageByItemCore implements GetStorageByItemOperation {
    @Override
    public GetStorageByItemResult process(GetStorageByItemInput input) {
        return null;
    }
}
