package com.devsteve.product_mservice.domain.ports.out;

import com.devsteve.product_mservice.domain.model.ImagenModel;

import java.util.List;

public interface ImagenRepository {
    ImagenModel guardar(ImagenModel imagenModel);
    ImagenModel buscarPorId(Long id);
    List<ImagenModel> buscarPorProductoId(Long productoId);
    void eliminar(ImagenModel imagen);
}
