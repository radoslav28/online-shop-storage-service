package com.onlineshop.storage.core.converters.storage;

import com.onlineshop.storage.api.operations.storage.get.byitem.GetStorageByItemResult;
import com.onlineshop.storage.persistence.entities.Storage;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StorageToGetStorageByItemResult implements Converter<Storage, GetStorageByItemResult> {
    @Override
    public GetStorageByItemResult convert(Storage source) {
        return GetStorageByItemResult
                .builder()
                .id(String.valueOf(source.getId()))
                .itemId(String.valueOf(source.getItemId()))
                .price(source.getPrice())
                .quantity(source.getQuantity())
                .build();
    }
}
