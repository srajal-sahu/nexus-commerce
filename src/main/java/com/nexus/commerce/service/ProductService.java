package com.nexus.commerce.service;

import com.nexus.commerce.dto.PageResponse;
import com.nexus.commerce.dto.ProductRequest;
import com.nexus.commerce.dto.ProductResponse;
import com.nexus.commerce.dto.ProductSearchCriteria;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    ProductResponse createProduct(ProductRequest request);
    ProductResponse getProductById(Long id);
    ProductResponse getProductBySlug(String slug);
    PageResponse<ProductResponse> searchProducts(ProductSearchCriteria criteria, Pageable pageable);
    ProductResponse updateProduct(Long id, ProductRequest request);
    void deleteProduct(Long id);
}
