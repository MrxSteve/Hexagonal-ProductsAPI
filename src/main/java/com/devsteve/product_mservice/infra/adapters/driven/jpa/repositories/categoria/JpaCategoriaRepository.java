package com.devsteve.product_mservice.infra.adapters.driven.jpa.repositories.categoria;

import com.devsteve.product_mservice.infra.adapters.driven.jpa.entities.CategoriaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCategoriaRepository extends JpaRepository<CategoriaEntity, Long> {
    Page<CategoriaEntity> findByNombreContainingIgnoreCase(String nombre, Pageable pageable);
    boolean existsByNombreIgnoreCase(String nombre);
}
