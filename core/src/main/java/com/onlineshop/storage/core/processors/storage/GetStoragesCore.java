package com.onlineshop.storage.core.processors.storage;

import com.onlineshop.storage.api.operations.storage.get.all.GetStoragesInput;
import com.onlineshop.storage.api.operations.storage.get.all.GetStoragesOperation;
import com.onlineshop.storage.api.operations.storage.get.all.GetStoragesResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetStoragesCore implements GetStoragesOperation {
    @Override
    public GetStoragesResult process(GetStoragesInput input) {
        return null;
    }
}
