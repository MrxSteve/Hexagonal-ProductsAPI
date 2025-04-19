package com.devsteve.product_mservice.domain.ports.out;

import com.devsteve.product_mservice.domain.model.ProductoModel;
import com.devsteve.product_mservice.domain.model.enums.EstadoProducto;

import java.util.List;
import java.util.Optional;

public interface ProductoRepository {
    ProductoModel guardar(ProductoModel productoModel);

    ProductoModel buscarPorId(Long id);

    List<ProductoModel> buscarTodas(int page, int size);

    void eliminar(Long id);

    boolean existePorNombre(String nombre);

    List<ProductoModel> buscarConFiltros(
            Optional<String> nombre,
            Optional<Long> marcaId,
            Optional<Long> categoriaId,
            Optional<EstadoProducto> estado,
            int page,
            int size
    );
}
