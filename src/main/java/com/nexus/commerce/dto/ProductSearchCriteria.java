package com.nexus.commerce.dto;

import java.math.BigDecimal;

public record ProductSearchCriteria(
        String query,
        Long categoryId,
        BigDecimal minPrice,
        BigDecimal maxPrice,
        Boolean inStockOnly
) {}
