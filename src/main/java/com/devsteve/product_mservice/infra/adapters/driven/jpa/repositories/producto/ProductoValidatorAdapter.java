package com.devsteve.product_mservice.infra.adapters.driven.jpa.repositories.producto;

import com.devsteve.product_mservice.domain.ports.out.ProductoValidatorPort;
import com.devsteve.product_mservice.shared.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductoValidatorAdapter implements ProductoValidatorPort {
    private final JpaProductoRepository jpaProductoRepository;

    @Override
    public void validarProductoExistente(Long productoId) {
        if (!jpaProductoRepository.existsById(productoId)) {
            throw new ResourceNotFoundException("Producto con id " + productoId + " no encontrado");
        }
    }
}
