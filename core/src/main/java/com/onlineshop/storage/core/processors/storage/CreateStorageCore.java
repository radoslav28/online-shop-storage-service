package com.onlineshop.storage.core.processors.storage;

import com.onlineshop.storage.api.exceptions.InvalidPriceException;
import com.onlineshop.storage.api.exceptions.NotExistingItemException;
import com.onlineshop.storage.api.operations.storage.create.CreateStorageInput;
import com.onlineshop.storage.api.operations.storage.create.CreateStorageOperation;
import com.onlineshop.storage.api.operations.storage.create.CreateStorageResult;
import com.onlineshop.storage.persistence.entities.Storage;
import com.onlineshop.storage.persistence.repositories.StorageRepository;
import com.onlineshop.store.api.exceptions.ServiceUnavailableException;
import com.onlineshop.store.restexport.StoreServiceRestClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CreateStorageCore implements CreateStorageOperation {

    private final StoreServiceRestClient storeServiceRestClient;
    private final StorageRepository storageRepository;
    private final ConversionService conversionService;

    @Override
    public CreateStorageResult process(CreateStorageInput input) {

        try {
            storeServiceRestClient.getItem(input.getItemId());
        } catch (FeignException.ServiceUnavailable e) {
            throw new ServiceUnavailableException();
        } catch (FeignException.NotFound e) {
            throw new NotExistingItemException(input.getItemId());
        }

        try {

            priceCheck(input.getPrice());

            Storage storage = conversionService.convert(input, Storage.class);

            Storage persisted = storageRepository.save(storage);

            return CreateStorageResult
                    .builder()
                    .id(String.valueOf(persisted.getId()))
                    .build();

        } catch (JDBCConnectionException e) {
            throw new ServiceUnavailableException();
        }
    }

    private void priceCheck(BigDecimal price) {
        if (price == null && !(price.compareTo(BigDecimal.valueOf(0)) > 0)){
            throw new InvalidPriceException();
        }
    }
}
