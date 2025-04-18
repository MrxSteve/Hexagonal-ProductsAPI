package com.devsteve.product_mservice.application.ports.in.marca;

import com.devsteve.product_mservice.domain.model.MarcaModel;

import java.util.List;

public interface BuscarMarcaPorNombreUseCase {
    List<MarcaModel> buscarPorNombre(String nombre, int page, int size);
}
