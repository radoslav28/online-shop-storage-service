package com.onlineshop.storage.core.processors.storage;

import com.onlineshop.storage.api.exceptions.DisallowedIdException;
import com.onlineshop.storage.api.exceptions.InvalidQuantityException;
import com.onlineshop.storage.api.exceptions.NotExistingItemException;
import com.onlineshop.storage.api.operations.storage.imp.ImportStorageInput;
import com.onlineshop.storage.api.operations.storage.imp.ImportStorageOperation;
import com.onlineshop.storage.api.operations.storage.imp.ImportStorageResult;
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
public class ImportStorageCore implements ImportStorageOperation {

    private final StorageRepository storageRepository;
    private final ConversionService conversionService;

    @Override
    public ImportStorageResult process(ImportStorageInput input) {

        try {

            Storage storage = storageRepository
                    .findByItemId(UUID.fromString(input.getItemId()))
                    .orElseThrow(() -> new NotExistingItemException(input.getItemId()));

            storage.setQuantity(storage.getQuantity() + quantityCheck(input.getQuantity()));
            storageRepository.save(storage);

            return conversionService.convert(storage, ImportStorageResult.class);

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
