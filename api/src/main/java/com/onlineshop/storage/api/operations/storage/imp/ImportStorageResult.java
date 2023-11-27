package com.onlineshop.storage.api.operations.storage.imp;

import com.onlineshop.storage.api.base.ProcessorResult;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImportStorageResult implements ProcessorResult {

    private String id;

    private String itemId;

    private Long quantity;
}
