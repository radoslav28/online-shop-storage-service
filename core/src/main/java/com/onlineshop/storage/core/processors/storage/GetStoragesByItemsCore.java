package com.onlineshop.storage.core.processors.storage;

import com.onlineshop.storage.api.exceptions.ServiceUnavailableException;
import com.onlineshop.storage.api.model.StorageModel;
import com.onlineshop.storage.api.operations.storage.get.all.GetStoragesResult;
import com.onlineshop.storage.api.operations.storage.get.byitems.GetStoragesByItemsInput;
import com.onlineshop.storage.api.operations.storage.get.byitems.GetStoragesByItemsOperation;
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
public class GetStoragesByItemsCore implements GetStoragesByItemsOperation {

    private final StorageRepository storageRepository;
    private final ConversionService conversionService;

    @Override
    public GetStoragesByItemsResult process(GetStoragesByItemsInput input) {

        try {

            List<Storage> storages = storageRepository.findByItemIdIn(input
                    .getItemsIds()
                    .stream()
                    .map(UUID::fromString)
                    .toList());

            List<StorageModel> storageModels = storages
                    .stream()
                    .map(s -> conversionService.convert(s, StorageModel.class))
                    .toList();

            return GetStoragesByItemsResult
                    .builder()
                    .storages(storageModels)
                    .build();

        } catch (JDBCConnectionException e) {
            throw new ServiceUnavailableException();
        }
    }
}
