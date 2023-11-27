package com.onlineshop.storage.core.processors.storage;

import com.onlineshop.storage.api.exceptions.DisallowedIdException;
import com.onlineshop.storage.api.exceptions.ServiceUnavailableException;
import com.onlineshop.storage.api.operations.storage.get.byid.GetStorageByIdInput;
import com.onlineshop.storage.api.operations.storage.get.byid.GetStorageByIdOperation;
import com.onlineshop.storage.api.operations.storage.get.byid.GetStorageByIdResult;
import com.onlineshop.storage.persistence.entities.Storage;
import com.onlineshop.storage.persistence.repositories.StorageRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetStorageByIdCore implements GetStorageByIdOperation {

    private final StorageRepository storageRepository;
    private final ConversionService conversionService;

    @Override
    public GetStorageByIdResult process(GetStorageByIdInput input) {

        try {

            Storage storage = storageRepository
                    .findById(UUID.fromString(input.getStorageId()))
                    .orElseThrow(() -> new DisallowedIdException(input.getStorageId()));

            return conversionService.convert(storage, GetStorageByIdResult.class);

        } catch (JDBCConnectionException e) {
            throw new ServiceUnavailableException();
        }
    }
}
