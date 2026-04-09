package com.warehouse.shop.repository;

import com.warehouse.shop.entity.Variant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VariantRepository extends JpaRepository<Variant, Long> {
}
