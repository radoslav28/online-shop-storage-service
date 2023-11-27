package com.onlineshop.storage.persistence.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sales")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID id;

    @Column(nullable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID userId;

    @Column(nullable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID itemId;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime saleDate;

    @Min(1)
    @Column(nullable = false)
    private Long quantity;

    @Column(nullable = false)
    private BigDecimal price;

    @Builder
    public Sale(UUID id, UUID userId, UUID itemId, Long quantity, BigDecimal price) {
        this.id = id;
        this.userId = userId;
        this.itemId = itemId;
        this.saleDate = LocalDateTime.now(ZoneOffset.UTC);
        this.quantity = quantity;
        this.price = price;
    }
}
