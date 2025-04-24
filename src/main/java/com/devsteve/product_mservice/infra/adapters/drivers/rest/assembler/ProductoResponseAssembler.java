package com.devsteve.product_mservice.infra.adapters.drivers.rest.assembler;

import com.devsteve.product_mservice.application.dto.res.ImagenResponse;
import com.devsteve.product_mservice.application.dto.res.ProductoResponse;
import com.devsteve.product_mservice.domain.model.ProductoModel;

import java.util.List;

public class ProductoResponseAssembler {
    public static ProductoResponse toResponse(
            ProductoModel model,
            String marcaNombre,
            String categoriaNombre,
            List<ImagenResponse> imagenes // Nuevo campo
    ) {
        ProductoResponse response = new ProductoResponse();
        response.setId(model.getId());
        response.setNombre(model.getNombre());
        response.setDescripcion(model.getDescripcion());
        response.setPrecio(model.getPrecio());

        response.setMarcaId(model.getMarcaId());
        response.setMarcaNombre(marcaNombre);

        response.setCategoriaId(model.getCategoriaId());
        response.setCategoriaNombre(categoriaNombre);

        response.setEstado(model.getEstado());
        response.setFechaCreacion(model.getFechaCreacion());

        response.setImagenes(imagenes); // Asignar la lista de imagenes

        return response;
    }
}
