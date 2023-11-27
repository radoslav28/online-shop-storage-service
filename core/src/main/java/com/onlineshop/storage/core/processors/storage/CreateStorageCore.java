package com.onlineshop.storage.core.processors.storage;

import com.onlineshop.storage.api.operations.storage.create.CreateStorageInput;
import com.onlineshop.storage.api.operations.storage.create.CreateStorageOperation;
import com.onlineshop.storage.api.operations.storage.create.CreateStorageResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateStorageCore implements CreateStorageOperation {
    @Override
    public CreateStorageResult process(CreateStorageInput input) {
        return null;
    }
}
