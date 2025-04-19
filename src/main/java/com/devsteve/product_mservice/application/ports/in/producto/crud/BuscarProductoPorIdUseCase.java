package com.devsteve.product_mservice.application.ports.in.producto.crud;

import com.devsteve.product_mservice.domain.model.ProductoModel;

public interface BuscarProductoPorIdUseCase {
    ProductoModel buscarPorId(Long id);
}
