package com.onlineshop.storage.api.operations.storage.create;

import com.onlineshop.storage.api.base.ProcessorInput;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import org.hibernate.validator.constraints.UUID;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateStorageInput implements ProcessorInput {

    @UUID
    @NotBlank
    private String itemId;

    @NotNull
    @PositiveOrZero
    private BigDecimal price;
}
