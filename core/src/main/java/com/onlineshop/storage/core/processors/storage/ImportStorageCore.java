package com.onlineshop.storage.core.processors.storage;

import com.onlineshop.storage.api.operations.storage.imp.ImportStorageInput;
import com.onlineshop.storage.api.operations.storage.imp.ImportStorageOperation;
import com.onlineshop.storage.api.operations.storage.imp.ImportStorageResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImportStorageCore implements ImportStorageOperation {
    @Override
    public ImportStorageResult process(ImportStorageInput input) {
        return null;
    }
}
