package com.onlineshop.storage.api.operations.storage.imp;

import com.onlineshop.storage.api.base.ProcessorInput;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.hibernate.validator.constraints.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImportStorageInput implements ProcessorInput {

    @UUID
    @NotBlank
    private String itemId;

    @NotNull
    @Positive
    private Long quantity;
}
