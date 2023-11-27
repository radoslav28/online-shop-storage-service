package com.onlineshop.storage.api.operations.sales.create;

import com.onlineshop.storage.api.base.ProcessorInput;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.UUID;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateSalesInput implements ProcessorInput{

    @UUID
    @NotBlank
    private String userId;

    private Map<@UUID String, Long> items;
}
