package com.devsteve.product_mservice.application.ports.in.categoria;

import com.devsteve.product_mservice.domain.model.CategoriaModel;

import java.util.List;

public interface ListarCategoriasUseCase {
    List<CategoriaModel> listarCategorias(int page, int size);
}
