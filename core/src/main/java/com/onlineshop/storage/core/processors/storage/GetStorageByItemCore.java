package com.onlineshop.storage.core.processors.storage;

import com.onlineshop.storage.api.exceptions.NotExistingItemException;
import com.onlineshop.storage.api.exceptions.ServiceUnavailableException;
import com.onlineshop.storage.api.model.StorageModel;
import com.onlineshop.storage.api.operations.storage.get.byitem.GetStorageByItemInput;
import com.onlineshop.storage.api.operations.storage.get.byitem.GetStorageByItemOperation;
import com.onlineshop.storage.api.operations.storage.get.byitem.GetStorageByItemResult;
import com.onlineshop.storage.api.operations.storage.get.byitems.GetStoragesByItemsResult;
import com.onlineshop.storage.persistence.entities.Storage;
import com.onlineshop.storage.persistence.repositories.StorageRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetStorageByItemCore implements GetStorageByItemOperation {

    private final StorageRepository storageRepository;
    private final ConversionService conversionService;

    @Override
    public GetStorageByItemResult process(GetStorageByItemInput input) {

        try {

            Storage storage = storageRepository
                    .findByItemId(UUID.fromString(input.getItemId()))
                    .orElseThrow(() -> new NotExistingItemException(input.getItemId()));

            return conversionService.convert(storage, GetStorageByItemResult.class);

        } catch (JDBCConnectionException e) {
            throw new ServiceUnavailableException();
        }
    }
}
