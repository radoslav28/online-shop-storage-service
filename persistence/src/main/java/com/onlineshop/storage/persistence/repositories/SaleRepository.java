package com.onlineshop.storage.persistence.repositories;

import com.onlineshop.storage.persistence.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SaleRepository extends JpaRepository<Sale, UUID> {
    List<Sale> findByUserId (UUID userId);
    List<Sale> findByItemId (UUID itemId);
 }
