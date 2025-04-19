package com.devsteve.product_mservice.infra.adapters.driven.jpa.repositories.producto;

import com.devsteve.product_mservice.domain.model.enums.EstadoProducto;
import com.devsteve.product_mservice.infra.adapters.driven.jpa.entities.ProductoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface JpaProductoRepository extends JpaRepository<ProductoEntity, Long>,
        JpaSpecificationExecutor<ProductoEntity> {
    boolean existsByNombreIgnoreCase(String nombre);

    // Los vamos hacer con especificaciones
//    Page<ProductoEntity> findByNombreContainingIgnoreCase(String nombre, Pageable pageable);
//    Page<ProductoEntity> findByMarcaId(Long marcaId, Pageable pageable);
//    Page<ProductoEntity> findByCategoriaId(Long categoriaId, Pageable pageable);
//    Page<ProductoEntity> findByEstado(EstadoProducto estado, Pageable pageable);
}
