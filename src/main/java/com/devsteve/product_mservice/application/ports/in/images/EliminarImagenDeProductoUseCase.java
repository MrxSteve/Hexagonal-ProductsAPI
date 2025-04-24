package com.devsteve.product_mservice.application.ports.in.images;

public interface EliminarImagenDeProductoUseCase {
    void eliminarImagenDeProducto(Long productoId, Long imagenId);
}
