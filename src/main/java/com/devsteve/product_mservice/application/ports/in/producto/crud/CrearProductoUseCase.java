package com.devsteve.product_mservice.application.ports.in.producto.crud;

import com.devsteve.product_mservice.domain.model.ProductoModel;

public interface CrearProductoUseCase {
    ProductoModel crearProducto(ProductoModel productoModel);
}
