package com.onlineshop.storage.rest.controllers;

import com.onlineshop.storage.api.operations.sales.create.CreateSalesInput;
import com.onlineshop.storage.api.operations.sales.create.CreateSalesOperation;
import com.onlineshop.storage.api.operations.sales.create.CreateSalesResult;
import com.onlineshop.storage.api.operations.sales.get.byitem.GetSalesByItemInput;
import com.onlineshop.storage.api.operations.sales.get.byitem.GetSalesByItemOperation;
import com.onlineshop.storage.api.operations.sales.get.byuser.GetSalesByUserInput;
import com.onlineshop.storage.api.operations.sales.get.byuser.GetSalesByUserOperation;
import com.onlineshop.storage.api.operations.sales.get.byuser.GetSalesByUserResult;
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

@RestController
@RequestMapping("/sales")
@Tag(name = "sales Controller")
@RequiredArgsConstructor
@Validated
public class SaleController {
    private final CreateSalesOperation createSales;
    private final GetSalesByItemOperation getSalesByItem;
    private final GetSalesByUserOperation getSalesByUser;

    @Operation(summary = "Create sales", description = "Create sales")
    @PostMapping
    public ResponseEntity<CreateSalesResult> createSales (@RequestBody @Valid CreateSalesInput input) {

        return new ResponseEntity<>(createSales.process(input), HttpStatus.CREATED);
    }

    @Operation(summary = "Get user sales", description = "Get sales for user")
    @GetMapping("/user")
    public ResponseEntity<GetSalesByUserResult> getSalesByUser (@RequestParam @UUID @NotBlank String userId) {

        GetSalesByUserInput input = GetSalesByUserInput
                .builder()
                .userId(userId)
                .build();

        return new ResponseEntity<>(getSalesByUser.process(input), HttpStatus.OK);
    }

    @Operation(summary = "Get item sales", description = "Get sales for item")
    @GetMapping("/item")
    public ResponseEntity<?> getItemSales (@RequestParam @UUID @NotBlank String itemId) {

        GetSalesByItemInput input = GetSalesByItemInput
                .builder()
                .itemId(itemId)
                .build();

        return new ResponseEntity<>(getSalesByItem.process(input), HttpStatus.OK);
    }
}
