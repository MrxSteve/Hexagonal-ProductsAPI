package com.devsteve.product_mservice.infra.adapters.driven.jpa.repositories.marca;

import com.devsteve.product_mservice.infra.adapters.driven.jpa.entities.MarcaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMarcaRepository extends JpaRepository<MarcaEntity, Long> {
    Page<MarcaEntity> findByNombreContainingIgnoreCase(String nombre, Pageable pageable);
    boolean existsByNombreIgnoreCase(String nombre);
}
