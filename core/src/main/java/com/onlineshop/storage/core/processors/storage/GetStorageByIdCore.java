package com.onlineshop.storage.core.processors.storage;

import com.onlineshop.storage.api.operations.storage.get.byid.GetStorageByIdInput;
import com.onlineshop.storage.api.operations.storage.get.byid.GetStorageByIdOperation;
import com.onlineshop.storage.api.operations.storage.get.byid.GetStorageByIdResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetStorageByIdCore implements GetStorageByIdOperation {
    @Override
    public GetStorageByIdResult process(GetStorageByIdInput input) {
        return null;
    }
}
