package com.onlineshop.storage.core.converters.storage;

import com.onlineshop.storage.api.operations.storage.imp.ImportStorageResult;
import com.onlineshop.storage.persistence.entities.Storage;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StorageToImportStorageResult implements Converter<Storage, ImportStorageResult> {
    @Override
    public ImportStorageResult convert(Storage source) {
        return ImportStorageResult
                .builder()
                .id(String.valueOf(source.getId()))
                .itemId(String.valueOf(source.getItemId()))
                .quantity(source.getQuantity())
                .build();
    }
}
