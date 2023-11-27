package com.onlineshop.storage.core.processors.storage;

import com.onlineshop.storage.api.operations.storage.delete.DeleteStorageInput;
import com.onlineshop.storage.api.operations.storage.delete.DeleteStorageOperation;
import com.onlineshop.storage.api.operations.storage.delete.DeleteStorageResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteStorageCore implements DeleteStorageOperation {
    @Override
    public DeleteStorageResult process(DeleteStorageInput input) {
        return null;
    }
}
