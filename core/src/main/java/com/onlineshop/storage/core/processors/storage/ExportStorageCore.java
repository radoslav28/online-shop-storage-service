package com.onlineshop.storage.core.processors.storage;

import com.onlineshop.storage.api.exceptions.InvalidQuantityException;
import com.onlineshop.storage.api.exceptions.NotEnoughQuantityException;
import com.onlineshop.storage.api.exceptions.NotExistingItemException;
import com.onlineshop.storage.api.operations.storage.exp.ExportStorageInput;
import com.onlineshop.storage.api.operations.storage.exp.ExportStorageOperation;
import com.onlineshop.storage.api.operations.storage.exp.ExportStorageResult;
import com.onlineshop.storage.persistence.entities.Storage;
import com.onlineshop.storage.persistence.repositories.StorageRepository;
import com.onlineshop.store.api.exceptions.ServiceUnavailableException;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExportStorageCore implements ExportStorageOperation {

    private final StorageRepository storageRepository;
    private final ConversionService conversionService;

    @Override
    public ExportStorageResult process(ExportStorageInput input) {

        try {

            Storage storage = storageRepository
                    .findByItemId(UUID.fromString(input.getItemId()))
                    .orElseThrow(() -> new NotExistingItemException(input.getItemId()));

            Long quantity = storage.getQuantity() - quantityCheck(input.getQuantity());
            if (quantity < 0) {
                throw new NotEnoughQuantityException();
            }
            storage.setQuantity(quantity);
            storageRepository.save(storage);

            return conversionService.convert(storage, ExportStorageResult.class);

        } catch (JDBCConnectionException e) {
            throw new ServiceUnavailableException();
        }
    }

    private Long quantityCheck(Long quantity) {

        if (quantity == null || quantity <= 0) {
            throw new InvalidQuantityException();
        }

        return quantity;
    }
}
