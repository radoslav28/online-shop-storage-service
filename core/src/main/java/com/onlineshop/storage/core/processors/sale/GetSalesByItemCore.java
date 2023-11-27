package com.onlineshop.storage.core.processors.sale;

import com.onlineshop.storage.api.exceptions.NotExistingItemException;
import com.onlineshop.storage.api.exceptions.ServiceUnavailableException;
import com.onlineshop.storage.api.model.SaleModel;
import com.onlineshop.storage.api.operations.sales.get.byitem.GetSalesByItemInput;
import com.onlineshop.storage.api.operations.sales.get.byitem.GetSalesByItemOperation;
import com.onlineshop.storage.api.operations.sales.get.byitem.GetSalesByItemResult;
import com.onlineshop.storage.persistence.entities.Sale;
import com.onlineshop.storage.persistence.repositories.SaleRepository;
import com.onlineshop.store.api.model.ItemModel;
import com.onlineshop.store.restexport.StoreServiceRestClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetSalesByItemCore implements GetSalesByItemOperation {

    private final SaleRepository saleRepository;
    private final StoreServiceRestClient storeServiceRestClient;
    private final ConversionService conversionService;

    @Override
    public GetSalesByItemResult process(GetSalesByItemInput input) {

        ItemModel item = getItem(input.getItemId());
        List<Sale> sales = getSales(input.getItemId());

        return GetSalesByItemResult
                .builder()
                .sales(sales
                        .stream()
                        .map(s -> conversionService.convert(s, SaleModel.class))
                        .peek(s -> s.setItem(item))
                        .toList())
                .build();
    }

    private ItemModel getItem (String itemId) {
        try {
            return conversionService.convert(storeServiceRestClient.getItem(itemId), ItemModel.class);
        } catch (FeignException.ServiceUnavailable e) {
            throw new ServiceUnavailableException();
        } catch (FeignException.NotFound e) {
            throw new NotExistingItemException(itemId);
        }
    }

    private List<Sale> getSales (String itemId) {
        try {
            return saleRepository.findByItemId(UUID.fromString(itemId));
        } catch (JDBCConnectionException e) {
            throw new ServiceUnavailableException();
        }
    }
}
