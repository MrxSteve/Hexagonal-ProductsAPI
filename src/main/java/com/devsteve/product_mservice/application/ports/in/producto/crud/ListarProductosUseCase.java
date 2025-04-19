package com.devsteve.product_mservice.application.ports.in.producto.crud;

import com.devsteve.product_mservice.domain.model.ProductoModel;

import java.util.List;

public interface ListarProductosUseCase {
    List<ProductoModel> listarProductos(int page, int size);
}
