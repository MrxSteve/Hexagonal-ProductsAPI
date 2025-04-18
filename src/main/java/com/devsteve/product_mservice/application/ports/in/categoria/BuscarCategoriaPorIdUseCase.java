package com.devsteve.product_mservice.application.ports.in.categoria;

import com.devsteve.product_mservice.domain.model.CategoriaModel;

public interface BuscarCategoriaPorIdUseCase {
    CategoriaModel buscarPorId(Long id);
}
