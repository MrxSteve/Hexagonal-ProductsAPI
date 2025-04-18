package com.devsteve.product_mservice.application.ports.in.marca;

import com.devsteve.product_mservice.domain.model.MarcaModel;

public interface BuscarMarcaPorIdUseCase {
    MarcaModel buscarPorId(Long id);
}
