package com.onlineshop.storage.api.operations.storage.create;

import com.onlineshop.storage.api.base.ProcessorResult;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateStorageResult implements ProcessorResult {
    private String id;
}
