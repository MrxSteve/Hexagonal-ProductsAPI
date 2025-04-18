package com.devsteve.product_mservice.application.ports.in.categoria;

import com.devsteve.product_mservice.domain.model.CategoriaModel;

public interface CrearCategoriaUseCase {
    CategoriaModel crearCategoria(CategoriaModel categoriaModel);
}
