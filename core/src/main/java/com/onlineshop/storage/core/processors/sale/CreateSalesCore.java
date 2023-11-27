package com.onlineshop.storage.core.processors.sale;

import com.onlineshop.storage.api.exceptions.NotExistingItemException;
import com.onlineshop.storage.api.exceptions.ServiceUnavailableException;
import com.onlineshop.storage.api.model.SaleModel;
import com.onlineshop.storage.api.operations.sales.create.CreateSalesInput;
import com.onlineshop.storage.api.operations.sales.create.CreateSalesOperation;
import com.onlineshop.storage.api.operations.sales.create.CreateSalesResult;
import com.onlineshop.storage.api.operations.storage.exp.ExportStorageInput;
import com.onlineshop.storage.api.operations.storage.exp.ExportStorageOperation;
import com.onlineshop.storage.persistence.entities.Sale;
import com.onlineshop.storage.persistence.entities.Storage;
import com.onlineshop.storage.persistence.repositories.SaleRepository;
import com.onlineshop.storage.persistence.repositories.StorageRepository;
import com.onlineshop.store.api.model.ItemModel;
import com.onlineshop.store.restexport.StoreServiceRestClient;
import feign.FeignException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateSalesCore implements CreateSalesOperation {

    private final SaleRepository saleRepository;
    private final StorageRepository storageRepository;
    private final ExportStorageOperation exportStorage;
    private final StoreServiceRestClient storeServiceRestClient;
    private final ConversionService conversionService;

    @Override
    @Transactional
    public CreateSalesResult process(CreateSalesInput input) {

        List<String> itemIds = input
                .getItems()
                .keySet()
                .stream()
                .toList();

        List<ItemModel> items = getItems(itemIds);
        List<Storage> storages = getStorages(itemIds);

        List<SaleModel> sales = new ArrayList<>();

        storages.forEach(storage -> {
            Long quantity = input
                    .getItems()
                    .get(String.valueOf(storage.getItemId()));

            exportStorage(String.valueOf(storage.getItemId()), quantity);

            Sale sale = Sale
                    .builder()
                    .userId(UUID.fromString(input.getUserId()))
                    .itemId(storage.getItemId())
                    .quantity(quantity)
                    .price(storage.getPrice().multiply(BigDecimal.valueOf(quantity)))
                    .build();

            Sale persisted;
            try {
                persisted = saleRepository.save(sale);
            } catch (JDBCConnectionException e) {
                throw new ServiceUnavailableException();
            }

            SaleModel sl = conversionService.convert(persisted, SaleModel.class);
            sl.setItem(items
                    .stream()
                    .filter(i -> i.getId().equals(String.valueOf(sale.getItemId())))
                    .findFirst()
                    .orElseThrow(() -> new NotExistingItemException(String.valueOf(sale.getItemId()))));

            sales.add(sl);
        });

        return CreateSalesResult
                .builder()
                .sales(sales)
                .build();
    }

    private List<ItemModel> getItems (List<String> itemIds) {
        try {

            return storeServiceRestClient
                    .getItemsByIds(itemIds)
                    .getItemModels();

        } catch (FeignException.ServiceUnavailable e) {
            throw new ServiceUnavailableException();
        } catch (FeignException.NotFound e) {
            throw new NotExistingItemException("");
        }
    }

    private List<Storage> getStorages (List<String> itemIds) {
        try {

            return storageRepository
                    .findByItemIdIn(itemIds
                            .stream()
                            .map(UUID::fromString)
                            .toList());

        } catch (JDBCConnectionException e) {
            throw new ServiceUnavailableException();
        }
    }

    private void exportStorage (String itemId, Long quantity) {
        ExportStorageInput exportStorageInput = ExportStorageInput
                .builder()
                .itemId(itemId)
                .quantity(quantity)
                .build();
        exportStorage.process(exportStorageInput);
    }
}
