package com.onlineshop.storage.core.converters.item;

import com.onlineshop.store.api.model.ItemModel;
import com.onlineshop.store.api.operations.item.get.byid.GetItemByIdResult;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class GetItemByIdResultToItemModel implements Converter<GetItemByIdResult, ItemModel> {
    @Override
    public ItemModel convert(GetItemByIdResult source) {
        return ItemModel
                .builder()
                .id(source.getId())
                .title(source.getTitle())
                .description(source.getDescription())
                .vendorModel(source.getVendorModel())
                .multimediaModels(source.getMultimediaModels())
                .tagModels(source.getTagModels())
                .build();
    }
}
