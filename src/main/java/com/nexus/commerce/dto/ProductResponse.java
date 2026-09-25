package com.nexus.commerce.dto;

import java.math.BigDecimal;
import java.time.Instant;

public record ProductResponse(
        Long id,
        String name,
        String slug,
        String description,
        String sku,
        BigDecimal price,
        int stockQuantity,
        boolean active,
        Long categoryId,
        String categoryName,
        Instant createdAt,
        Instant updatedAt
) {}
