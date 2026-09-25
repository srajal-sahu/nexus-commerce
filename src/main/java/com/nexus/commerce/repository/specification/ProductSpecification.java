package com.nexus.commerce.repository.specification;

import com.nexus.commerce.domain.Product;
import com.nexus.commerce.dto.ProductSearchCriteria;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ProductSpecification {

    public static Specification<Product> filterBy(ProductSearchCriteria criteria) {
        return (root, query, cb) -> {
            // Fetch join category only when query result is Product (not count query for pagination)
            if (query != null && !Long.class.equals(query.getResultType()) && !long.class.equals(query.getResultType())) {
                root.fetch("category", JoinType.INNER);
            }

            List<Predicate> predicates = new ArrayList<>();

            // Only active products
            predicates.add(cb.isTrue(root.get("active")));

            if (criteria == null) {
                return cb.and(predicates.toArray(new Predicate[0]));
            }

            // 1. Full text search in Name or Description
            if (criteria.query() != null && !criteria.query().isBlank()) {
                String searchPattern = "%" + criteria.query().trim().toLowerCase() + "%";
                Predicate nameMatch = cb.like(cb.lower(root.get("name")), searchPattern);
                Predicate descMatch = cb.like(cb.lower(root.get("description")), searchPattern);
                predicates.add(cb.or(nameMatch, descMatch));
            }

            // 2. Category ID filter
            if (criteria.categoryId() != null) {
                predicates.add(cb.equal(root.get("category").get("id"), criteria.categoryId()));
            }

            // 3. Minimum Price
            if (criteria.minPrice() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("price"), criteria.minPrice()));
            }

            // 4. Maximum Price
            if (criteria.maxPrice() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("price"), criteria.maxPrice()));
            }

            // 5. In-Stock Only filter
            if (Boolean.TRUE.equals(criteria.inStockOnly())) {
                predicates.add(cb.greaterThan(root.get("stockQuantity"), 0));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
