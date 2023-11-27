package com.onlineshop.storage.core.processors.storage;

import com.onlineshop.storage.api.exceptions.DisallowedIdException;
import com.onlineshop.storage.api.exceptions.InvalidPriceException;
import com.onlineshop.storage.api.operations.storage.changeprice.ChangeStoragePriceInput;
import com.onlineshop.storage.api.operations.storage.changeprice.ChangeStoragePriceOperation;
import com.onlineshop.storage.api.operations.storage.changeprice.ChangeStoragePriceResult;
import com.onlineshop.storage.persistence.entities.Storage;
import com.onlineshop.storage.persistence.repositories.StorageRepository;
import com.onlineshop.store.api.exceptions.ServiceUnavailableException;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChangeStoragePriceCore implements ChangeStoragePriceOperation {

    private final StorageRepository storageRepository;

    @Override
    public ChangeStoragePriceResult process(ChangeStoragePriceInput input) {

        try {

            Storage storage = storageRepository
                    .findById(UUID.fromString(input.getStorageId()))
                    .orElseThrow(() -> new DisallowedIdException(input.getStorageId()));

            storage.setPrice(priceCheck(input.getPrice()));
            storageRepository.save(storage);

            return ChangeStoragePriceResult.builder().build();

        } catch (JDBCConnectionException e) {
            throw new ServiceUnavailableException();
        }
    }

    private BigDecimal priceCheck(BigDecimal price) {

        if (price == null || !(price.compareTo(BigDecimal.valueOf(0)) > 0)){
            throw new InvalidPriceException();
        }

        return price;
    }
}
