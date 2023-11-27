package com.onlineshop.storage.core.converters.storage;

import com.onlineshop.storage.api.model.StorageModel;
import com.onlineshop.storage.persistence.entities.Storage;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StorageToStorageModel implements Converter<Storage, StorageModel> {
    @Override
    public StorageModel convert(Storage source) {
        return StorageModel
                .builder()
                .id(String.valueOf(source.getId()))
                .itemId(String.valueOf(source.getItemId()))
                .price(source.getPrice())
                .quantity(source.getQuantity())
                .build();
    }
}
