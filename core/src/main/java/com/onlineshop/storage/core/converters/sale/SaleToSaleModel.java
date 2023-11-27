package com.onlineshop.storage.core.converters.sale;

import com.onlineshop.storage.api.model.SaleModel;
import com.onlineshop.storage.persistence.entities.Sale;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SaleToSaleModel implements Converter<Sale, SaleModel> {
    @Override
    public SaleModel convert(Sale source) {
        return SaleModel
                .builder()
                .id(String.valueOf(source.getId()))
                .userId(String.valueOf(source.getUserId()))
                .quantity(source.getQuantity())
                .price(source.getPrice())
                .build();
    }
}
