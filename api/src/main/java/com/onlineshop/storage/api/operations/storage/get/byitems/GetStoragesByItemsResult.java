package com.onlineshop.storage.api.operations.storage.get.byitems;

import com.onlineshop.storage.api.base.ProcessorResult;
import com.onlineshop.storage.api.model.StorageModel;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetStoragesByItemsResult implements ProcessorResult {
    private List<StorageModel> storages;
}
