package com.devsteve.product_mservice.application.ports.in.producto.crud;

import com.devsteve.product_mservice.domain.model.ProductoModel;

public interface ActualizarProductoUseCase {
    ProductoModel actualizarProducto(Long id, ProductoModel model);
}
