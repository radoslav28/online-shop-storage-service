package com.onlineshop.storage.core.converters.storage;

import com.onlineshop.storage.api.operations.storage.create.CreateStorageInput;
import com.onlineshop.storage.persistence.entities.Storage;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreateStorageInputToStorage implements Converter<CreateStorageInput, Storage> {
    @Override
    public Storage convert(CreateStorageInput source) {
        return Storage
                .builder()
                .itemId(UUID.fromString(source.getItemId()))
                .price(source.getPrice())
                .quantity(0L)
                .build();
    }
}
