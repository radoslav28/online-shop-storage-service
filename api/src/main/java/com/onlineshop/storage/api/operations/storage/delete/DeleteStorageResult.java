package com.onlineshop.storage.api.operations.storage.delete;

import com.onlineshop.storage.api.base.ProcessorResult;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class DeleteStorageResult implements ProcessorResult {
    private final String message = "Deleted successfully";
}
