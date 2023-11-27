package com.onlineshop.storage.core.processors.storage;

import com.onlineshop.storage.api.operations.storage.get.byitems.GetStoragesByItemsInput;
import com.onlineshop.storage.api.operations.storage.get.byitems.GetStoragesByItemsOperation;
import com.onlineshop.storage.api.operations.storage.get.byitems.GetStoragesByItemsResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetStoragesByItemsCore implements GetStoragesByItemsOperation {
    @Override
    public GetStoragesByItemsResult process(GetStoragesByItemsInput input) {
        return null;
    }
}
