package com.devsteve.product_mservice.application.ports.in.marca;

import com.devsteve.product_mservice.domain.model.MarcaModel;

import java.util.List;

public interface ListarMarcasUseCase {
    List<MarcaModel> listarMarcas(int page, int size);
}
