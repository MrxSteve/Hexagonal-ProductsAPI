package com.devsteve.product_mservice.application.ports.in.categoria;

import com.devsteve.product_mservice.domain.model.CategoriaModel;

public interface ActualizarCategoriaUseCase {
    CategoriaModel actualizar(Long id, CategoriaModel model);
}
