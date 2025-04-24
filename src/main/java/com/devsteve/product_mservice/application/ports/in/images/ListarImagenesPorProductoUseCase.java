package com.devsteve.product_mservice.application.ports.in.images;

import com.devsteve.product_mservice.domain.model.ImagenModel;

import java.util.List;

public interface ListarImagenesPorProductoUseCase {
    List<ImagenModel> listarImagenes(Long productoId);
}
