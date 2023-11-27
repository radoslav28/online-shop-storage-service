package com.onlineshop.storage.api.operations.storage.changeprice;

import com.onlineshop.storage.api.base.ProcessorResult;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class ChangeStoragePriceResult implements ProcessorResult {

    private final String message = "Priced changes successfully";
}
