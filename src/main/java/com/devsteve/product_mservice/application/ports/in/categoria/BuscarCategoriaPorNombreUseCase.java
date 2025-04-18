package com.devsteve.product_mservice.application.ports.in.categoria;

import com.devsteve.product_mservice.domain.model.CategoriaModel;

import java.util.List;

public interface BuscarCategoriaPorNombreUseCase {
    List<CategoriaModel> buscarPorNombre(String nombre, int page, int size);
}
