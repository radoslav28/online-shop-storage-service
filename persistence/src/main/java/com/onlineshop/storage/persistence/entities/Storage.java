package com.onlineshop.storage.persistence.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "storages")
public class Storage {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID id;

    @Column(nullable = false, unique = true)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID itemId;

    @Min(0)
    @Column(nullable = false)
    private Long quantity;

    @Column(nullable = false)
    private BigDecimal price;

}
