package com.devsteve.product_mservice.application.ports.in.images;

import com.devsteve.product_mservice.domain.model.ImagenModel;

public interface BuscarImagenPorIdUseCase {
    ImagenModel buscarImagen(Long imagenId);
}
