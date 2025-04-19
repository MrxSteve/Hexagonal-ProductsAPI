package com.devsteve.product_mservice.application.ports.in.producto.filters;

import com.devsteve.product_mservice.domain.model.ProductoModel;
import com.devsteve.product_mservice.domain.model.enums.EstadoProducto;

import java.util.List;
import java.util.Optional;

public interface BuscarProductosPorFiltrosUseCase {
    List<ProductoModel> buscarConFiltros(
            Optional<String> nombre,
            Optional<Long> marcaId,
            Optional<Long> categoriaId,
            Optional<EstadoProducto> estado,
            int page,
            int size
    );
}
