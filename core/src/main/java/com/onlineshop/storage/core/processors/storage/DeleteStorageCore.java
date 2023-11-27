package com.onlineshop.storage.core.processors.storage;

import com.onlineshop.storage.api.operations.storage.delete.DeleteStorageInput;
import com.onlineshop.storage.api.operations.storage.delete.DeleteStorageOperation;
import com.onlineshop.storage.api.operations.storage.delete.DeleteStorageResult;
import com.onlineshop.storage.persistence.repositories.StorageRepository;
import com.onlineshop.store.api.exceptions.ServiceUnavailableException;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteStorageCore implements DeleteStorageOperation {

    private final StorageRepository storageRepository;

    @Override
    public DeleteStorageResult process(DeleteStorageInput input) {

        try {

            storageRepository.deleteById(UUID.fromString(input.getStorageId()));

            return DeleteStorageResult.builder().build();

        } catch (JDBCConnectionException e) {
            throw new ServiceUnavailableException();
        }

    }
}
