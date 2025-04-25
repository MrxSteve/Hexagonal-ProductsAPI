package com.devsteve.product_mservice.domain.ports.out;

public interface ProductoValidatorPort {
    void validarProductoExistente(Long productoId);
}
