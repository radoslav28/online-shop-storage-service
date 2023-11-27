package com.onlineshop.storage.restexport;

import com.onlineshop.storage.api.operations.sales.create.CreateSalesInput;
import com.onlineshop.storage.api.operations.sales.create.CreateSalesResult;
import com.onlineshop.storage.api.operations.sales.get.byitem.GetSalesByItemResult;
import com.onlineshop.storage.api.operations.sales.get.byuser.GetSalesByUserResult;
import com.onlineshop.storage.api.operations.storage.changeprice.ChangeStoragePriceInput;
import com.onlineshop.storage.api.operations.storage.changeprice.ChangeStoragePriceResult;
import com.onlineshop.storage.api.operations.storage.create.CreateStorageInput;
import com.onlineshop.storage.api.operations.storage.create.CreateStorageResult;
import com.onlineshop.storage.api.operations.storage.delete.DeleteStorageInput;
import com.onlineshop.storage.api.operations.storage.delete.DeleteStorageResult;
import com.onlineshop.storage.api.operations.storage.exp.ExportStorageInput;
import com.onlineshop.storage.api.operations.storage.exp.ExportStorageResult;
import com.onlineshop.storage.api.operations.storage.get.all.GetStoragesResult;
import com.onlineshop.storage.api.operations.storage.get.byid.GetStorageByIdResult;
import com.onlineshop.storage.api.operations.storage.get.byitem.GetStorageByItemResult;
import com.onlineshop.storage.api.operations.storage.get.byitems.GetStoragesByItemsResult;
import com.onlineshop.storage.api.operations.storage.imp.ImportStorageInput;
import com.onlineshop.storage.api.operations.storage.imp.ImportStorageResult;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

import java.util.List;

@Headers({"Content-Type: application/json"})
public interface StorageServiceRestClient {
    @RequestLine("POST /storage")
    CreateStorageResult createStorage (CreateStorageInput input);

    @RequestLine("GET /storage/{storageId}")
    GetStorageByIdResult getStorage (@Param("storageId") String storageId);

    @RequestLine("GET /storage/item?itemId={itemId}")
    GetStorageByItemResult getStorageByItem(@Param("itemId") String itemId);

    @RequestLine("GET /storage")
    GetStoragesResult getStorages ();

    @RequestLine("GET /storage/items?itemsIds={itemsIds}")
    GetStoragesByItemsResult getStoragesByItems (@Param("itemsIds") List<String> itemsIds);

    @RequestLine("DELETE /storage")
    DeleteStorageResult deleteStorage (DeleteStorageInput input);

    @Headers({"Content-Type: application/json-patch+json"})
    @RequestLine("PATCH /storage/import")
    ImportStorageResult importStorage (ImportStorageInput input);

    @Headers({"Content-Type: application/json-patch+json"})
    @RequestLine("PATCH /storage/export")
    ExportStorageResult exportStorage (ExportStorageInput input);

    @Headers({"Content-Type: application/json-patch+json"})
    @RequestLine("PATCH /storage/price")
    ChangeStoragePriceResult changeStoragePrice (ChangeStoragePriceInput input);

    @RequestLine("POST /sales")
    CreateSalesResult createSales (CreateSalesInput input);

    @RequestLine("GET /sales/user?userId={userId}")
    GetSalesByUserResult getSalesByUser (@Param("userId") String userId);

    @RequestLine("GET /sales/item?itemId={itemId}")
    GetSalesByItemResult getSalesByItem (@Param("itemId") String itemId);
}
