package com.onlineshop.storage.core.processors.sale;

import com.onlineshop.storage.api.exceptions.NotExistingItemException;
import com.onlineshop.storage.api.exceptions.ServiceUnavailableException;
import com.onlineshop.storage.api.model.SaleModel;
import com.onlineshop.storage.api.operations.sales.get.byitem.GetSalesByItemResult;
import com.onlineshop.storage.api.operations.sales.get.byuser.GetSalesByUserInput;
import com.onlineshop.storage.api.operations.sales.get.byuser.GetSalesByUserOperation;
import com.onlineshop.storage.api.operations.sales.get.byuser.GetSalesByUserResult;
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
public class GetSalesByUserCore implements GetSalesByUserOperation {

    private final SaleRepository saleRepository;
    private final StoreServiceRestClient storeServiceRestClient;
    private final ConversionService conversionService;

    @Override
    public GetSalesByUserResult process(GetSalesByUserInput input) {

        List<Sale> saleList = getSales(input.getUserId());
        List<ItemModel> items = getItems(saleList
                .stream()
                .map(s -> String.valueOf(s.getItemId()))
                .toList());

        List<SaleModel> sales = saleList
                .stream()
                .map(s -> {
                    SaleModel sale = conversionService.convert(s, SaleModel.class);
                    sale.setItem(items
                            .stream()
                            .filter(i -> i.getId().equals(String.valueOf(s.getItemId())))
                            .findFirst()
                            .orElseThrow(() -> new NotExistingItemException(String.valueOf(s.getItemId()))));
                    return sale;
                })
                .toList();

        return GetSalesByUserResult
                .builder()
                .sales(sales)
                .build();
    }

    private List<ItemModel> getItems (List<String> itemsIds) {
        try {
            return storeServiceRestClient
                    .getItemsByIds(itemsIds)
                    .getItemModels();
        } catch (FeignException.ServiceUnavailable e) {
            throw new ServiceUnavailableException();
        } catch (FeignException.NotFound e) {
            throw new NotExistingItemException("");
        }
    }

    private List<Sale> getSales (String userId) {
        try {
            return saleRepository.findByUserId(UUID.fromString(userId));
        } catch (JDBCConnectionException e) {
            throw new ServiceUnavailableException();
        }
    }
}
