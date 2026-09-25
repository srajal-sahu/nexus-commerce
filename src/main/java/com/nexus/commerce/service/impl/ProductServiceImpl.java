package com.nexus.commerce.service.impl;

import com.nexus.commerce.domain.Category;
import com.nexus.commerce.domain.Product;
import com.nexus.commerce.dto.PageResponse;
import com.nexus.commerce.dto.ProductRequest;
import com.nexus.commerce.dto.ProductResponse;
import com.nexus.commerce.dto.ProductSearchCriteria;
import com.nexus.commerce.exception.BadRequestException;
import com.nexus.commerce.exception.ResourceNotFoundException;
import com.nexus.commerce.repository.CategoryRepository;
import com.nexus.commerce.repository.ProductRepository;
import com.nexus.commerce.repository.specification.ProductSpecification;
import com.nexus.commerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public ProductResponse createProduct(ProductRequest request) {
        if (productRepository.existsBySku(request.sku())) {
            throw new BadRequestException("Product with SKU '" + request.sku() + "' already exists");
        }

        Category category = categoryRepository.findById(request.categoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + request.categoryId()));

        String slug = toSlug(request.name());
        if (productRepository.existsBySlug(slug)) {
            slug = slug + "-" + request.sku().toLowerCase();
        }

        Product product = Product.builder()
                .name(request.name().trim())
                .slug(slug)
                .description(request.description())
                .sku(request.sku().trim().toUpperCase())
                .price(request.price())
                .stockQuantity(request.stockQuantity())
                .active(true)
                .category(category)
                .build();

        Product saved = productRepository.save(product);
        return mapToResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductResponse getProductById(Long id) {
        Product product = productRepository.findWithCategoryById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        return mapToResponse(product);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductResponse getProductBySlug(String slug) {
        Product product = productRepository.findWithCategoryBySlug(slug)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with slug: " + slug));
        return mapToResponse(product);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponse<ProductResponse> searchProducts(ProductSearchCriteria criteria, Pageable pageable) {
        Specification<Product> spec = ProductSpecification.filterBy(criteria);
        Page<Product> page = productRepository.findAll(spec, pageable);
        return PageResponse.from(page.map(this::mapToResponse));
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));

        if (!product.getSku().equalsIgnoreCase(request.sku()) && productRepository.existsBySku(request.sku())) {
            throw new BadRequestException("Product with SKU '" + request.sku() + "' already exists");
        }

        Category category = categoryRepository.findById(request.categoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + request.categoryId()));

        product.setName(request.name().trim());
        product.setSlug(toSlug(request.name()) + "-" + request.sku().toLowerCase());
        product.setDescription(request.description());
        product.setSku(request.sku().trim().toUpperCase());
        product.setPrice(request.price());
        product.setStockQuantity(request.stockQuantity());
        product.setCategory(category);

        return mapToResponse(productRepository.save(product));
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        product.setActive(false);
        productRepository.save(product);
    }

    private ProductResponse mapToResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getSlug(),
                product.getDescription(),
                product.getSku(),
                product.getPrice(),
                product.getStockQuantity(),
                product.isActive(),
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getCreatedAt(),
                product.getUpdatedAt()
        );
    }

    private String toSlug(String input) {
        return input.toLowerCase()
                .replaceAll("[^a-z0-9\\s]", "")
                .replaceAll("\\s+", "-");
    }
}
