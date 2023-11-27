package com.onlineshop.storage.core.processors.storage;

import com.onlineshop.storage.api.operations.storage.exp.ExportStorageInput;
import com.onlineshop.storage.api.operations.storage.exp.ExportStorageOperation;
import com.onlineshop.storage.api.operations.storage.exp.ExportStorageResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExportStorageCore implements ExportStorageOperation {
    @Override
    public ExportStorageResult process(ExportStorageInput input) {
        return null;
    }
}
