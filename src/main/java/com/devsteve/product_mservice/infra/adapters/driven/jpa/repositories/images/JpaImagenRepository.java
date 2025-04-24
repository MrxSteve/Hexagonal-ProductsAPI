package com.devsteve.product_mservice.infra.adapters.driven.jpa.repositories.images;

import com.devsteve.product_mservice.infra.adapters.driven.jpa.entities.ImagenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaImagenRepository extends JpaRepository<ImagenEntity, Long> {
    List<ImagenEntity> findByProductoEntity_Id(Long productoId);
}
