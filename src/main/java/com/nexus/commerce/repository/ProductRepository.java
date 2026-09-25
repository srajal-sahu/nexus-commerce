package com.nexus.commerce.repository;

import com.nexus.commerce.domain.Product;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    @EntityGraph(attributePaths = {"category"})
    Optional<Product> findWithCategoryById(Long id);

    @EntityGraph(attributePaths = {"category"})
    Optional<Product> findWithCategoryBySlug(String slug);

    boolean existsBySku(String sku);
    boolean existsBySlug(String slug);
}
