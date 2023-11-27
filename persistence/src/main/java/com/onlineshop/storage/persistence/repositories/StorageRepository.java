package com.onlineshop.storage.persistence.repositories;

import com.onlineshop.storage.persistence.entities.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface StorageRepository extends JpaRepository<Storage, UUID> {
    Optional<Storage> findByItemId(UUID itemId);
    List<Storage> findByItemIdIn (List<UUID> itemIds);
}
