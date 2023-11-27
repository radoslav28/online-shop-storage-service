package com.onlineshop.storage.core.converters.storage;

import com.onlineshop.storage.api.operations.storage.get.byid.GetStorageByIdResult;
import com.onlineshop.storage.persistence.entities.Storage;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StorageToGetStorageByIdResult implements Converter<Storage, GetStorageByIdResult> {
    @Override
    public GetStorageByIdResult convert(Storage source) {
        return GetStorageByIdResult
                .builder()
                .id(String.valueOf(source.getId()))
                .itemId(String.valueOf(source.getItemId()))
                .price(source.getPrice())
                .quantity(source.getQuantity())
                .build();
    }
}
