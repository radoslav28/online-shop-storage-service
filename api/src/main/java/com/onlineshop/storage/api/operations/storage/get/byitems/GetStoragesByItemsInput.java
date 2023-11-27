package com.onlineshop.storage.api.operations.storage.get.byitems;

import com.onlineshop.storage.api.base.ProcessorInput;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.UUID;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetStoragesByItemsInput implements ProcessorInput {
    List<@UUID @NotBlank String> itemsIds;
}
