package com.onlineshop.storage.rest.controllers;

import com.onlineshop.storage.api.operations.storage.changeprice.ChangeStoragePriceInput;
import com.onlineshop.storage.api.operations.storage.changeprice.ChangeStoragePriceOperation;
import com.onlineshop.storage.api.operations.storage.changeprice.ChangeStoragePriceResult;
import com.onlineshop.storage.api.operations.storage.create.CreateStorageInput;
import com.onlineshop.storage.api.operations.storage.create.CreateStorageOperation;
import com.onlineshop.storage.api.operations.storage.create.CreateStorageResult;
import com.onlineshop.storage.api.operations.storage.delete.DeleteStorageInput;
import com.onlineshop.storage.api.operations.storage.delete.DeleteStorageOperation;
import com.onlineshop.storage.api.operations.storage.delete.DeleteStorageResult;
import com.onlineshop.storage.api.operations.storage.exp.ExportStorageInput;
import com.onlineshop.storage.api.operations.storage.exp.ExportStorageOperation;
import com.onlineshop.storage.api.operations.storage.exp.ExportStorageResult;
import com.onlineshop.storage.api.operations.storage.get.all.GetStoragesInput;
import com.onlineshop.storage.api.operations.storage.get.all.GetStoragesOperation;
import com.onlineshop.storage.api.operations.storage.get.all.GetStoragesResult;
import com.onlineshop.storage.api.operations.storage.get.byid.GetStorageByIdInput;
import com.onlineshop.storage.api.operations.storage.get.byid.GetStorageByIdOperation;
import com.onlineshop.storage.api.operations.storage.get.byitem.GetStorageByItemInput;
import com.onlineshop.storage.api.operations.storage.get.byitem.GetStorageByItemOperation;
import com.onlineshop.storage.api.operations.storage.get.byitem.GetStorageByItemResult;
import com.onlineshop.storage.api.operations.storage.get.byitems.GetStoragesByItemsInput;
import com.onlineshop.storage.api.operations.storage.get.byitems.GetStoragesByItemsOperation;
import com.onlineshop.storage.api.operations.storage.get.byitems.GetStoragesByItemsResult;
import com.onlineshop.storage.api.operations.storage.imp.ImportStorageInput;
import com.onlineshop.storage.api.operations.storage.imp.ImportStorageOperation;
import com.onlineshop.storage.api.operations.storage.imp.ImportStorageResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/storage")
@Tag(name = "Storage Controller")
@RequiredArgsConstructor
@Validated
public class StorageController {

    private final CreateStorageOperation createStorage;
    private final GetStorageByIdOperation getStorageById;
    private final GetStorageByItemOperation getStorageByItem;
    private final GetStoragesOperation getStorages;
    private final GetStoragesByItemsOperation getStoragesByItems;
    private final ImportStorageOperation importStorage;
    private final ExportStorageOperation exportStorage;
    private final ChangeStoragePriceOperation changeStoragePrice;
    private final DeleteStorageOperation deleteStorage;

    @Operation(summary = "Create storage", description = "Create storage")
    @PostMapping
    public ResponseEntity<CreateStorageResult> createStorage(@RequestBody @Valid CreateStorageInput input) {

        return new ResponseEntity<>(createStorage.process(input), HttpStatus.CREATED);
    }

    @Operation(summary = "Get storage", description = "Get storage")
    @GetMapping("/{storageId}")
    public ResponseEntity<?> getStorageById (@PathVariable @UUID @NotBlank String storageId) {

        GetStorageByIdInput input = GetStorageByIdInput
                .builder()
                .storageId(storageId)
                .build();

        return new ResponseEntity<>(getStorageById.process(input), HttpStatus.OK);
    }

    @Operation(summary = "Get storage by item", description = "Get storage by item id")
    @GetMapping("/item")
    public ResponseEntity<GetStorageByItemResult> getStorageByItem (@RequestParam @UUID @NotBlank String itemId) {

        GetStorageByItemInput input =  GetStorageByItemInput
                .builder()
                .itemId(itemId)
                .build();

        return new ResponseEntity<>(getStorageByItem.process(input), HttpStatus.OK);
    }

    @Operation(summary = "Get storages", description = "Get storages")
    @GetMapping
    public ResponseEntity<GetStoragesResult> getStorages() {

        return new ResponseEntity<>(getStorages.process(new GetStoragesInput()), HttpStatus.OK);
    }

    @Operation(summary = "Get storages by items", description = "Get storages by items ids")
    @GetMapping("/items")
    public ResponseEntity<GetStoragesByItemsResult> getStoragesByItems(@RequestParam List<@UUID @NotBlank String> itemsIds) {

        GetStoragesByItemsInput input = GetStoragesByItemsInput
                .builder()
                .itemsIds(itemsIds)
                .build();

        return new ResponseEntity<>(getStoragesByItems.process(input), HttpStatus.OK);
    }

    @Operation(summary = "Delete storage", description = "Delete storage")
    @DeleteMapping
    public ResponseEntity<DeleteStorageResult> deleteStorage(@RequestBody @Valid DeleteStorageInput input) {

        return new ResponseEntity<>(deleteStorage.process(input), HttpStatus.OK);
    }

    @Operation(summary = "Import storage", description = "Import storage")
    @PatchMapping(value = "/import", consumes = "application/json-patch+json")
    public ResponseEntity<ImportStorageResult> importStorage (@RequestBody @Valid ImportStorageInput input) {

        return new ResponseEntity<>(importStorage.process(input), HttpStatus.OK);
    }

    @Operation(summary = "Export storage", description = "Export storage")
    @PatchMapping(value = "/export", consumes = "application/json-patch+json")
    public ResponseEntity<ExportStorageResult> exportStorage (@RequestBody @Valid ExportStorageInput input) {

        return new ResponseEntity<>(exportStorage.process(input), HttpStatus.OK);
    }

    @Operation(summary = "Change price", description = "Changing storage price")
    @PatchMapping(value = "/price", consumes = "application/json-patch+json")
    public ResponseEntity<ChangeStoragePriceResult> changeStoragePrice (@RequestBody @Valid ChangeStoragePriceInput input) {

        return new ResponseEntity<>(changeStoragePrice.process(input), HttpStatus.OK);
    }
}
