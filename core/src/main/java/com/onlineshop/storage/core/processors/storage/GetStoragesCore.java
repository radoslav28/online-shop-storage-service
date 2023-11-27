package com.onlineshop.storage.core.processors.storage;

import com.onlineshop.storage.api.exceptions.ServiceUnavailableException;
import com.onlineshop.storage.api.model.StorageModel;
import com.onlineshop.storage.api.operations.storage.get.all.GetStoragesInput;
import com.onlineshop.storage.api.operations.storage.get.all.GetStoragesOperation;
import com.onlineshop.storage.api.operations.storage.get.all.GetStoragesResult;
import com.onlineshop.storage.persistence.entities.Storage;
import com.onlineshop.storage.persistence.repositories.StorageRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetStoragesCore implements GetStoragesOperation {

    private final StorageRepository storageRepository;
    private final ConversionService conversionService;

    @Override
    public GetStoragesResult process(GetStoragesInput input) {

        try {

            List<Storage> storages = storageRepository.findAll();

            List<StorageModel> storageModels = storages
                    .stream()
                    .map(s -> conversionService.convert(s, StorageModel.class))
                    .toList();

            return GetStoragesResult
                    .builder()
                    .storages(storageModels)
                    .build();

        } catch (JDBCConnectionException e) {
            throw new ServiceUnavailableException();
        }
    }
}
