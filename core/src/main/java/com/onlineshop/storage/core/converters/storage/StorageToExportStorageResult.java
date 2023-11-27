package com.onlineshop.storage.core.converters.storage;

import com.onlineshop.storage.api.operations.storage.exp.ExportStorageResult;
import com.onlineshop.storage.persistence.entities.Storage;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StorageToExportStorageResult implements Converter<Storage, ExportStorageResult> {
    @Override
    public ExportStorageResult convert(Storage source) {
        return ExportStorageResult
                .builder()
                .id(String.valueOf(source.getId()))
                .itemId(String.valueOf(source.getItemId()))
                .quantity(source.getQuantity())
                .build();
    }
}
