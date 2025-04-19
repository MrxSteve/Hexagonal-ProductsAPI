package com.devsteve.product_mservice.infra.adapters.driven.jpa.mappers.producto;

import com.devsteve.product_mservice.application.dto.req.CreateProductoRequest;
import com.devsteve.product_mservice.application.dto.req.UpdateProductoRequest;
import com.devsteve.product_mservice.application.dto.res.ProductoResponse;
import com.devsteve.product_mservice.domain.model.ProductoModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductoModelMapper {
    ProductoModelMapper INSTANCE = Mappers.getMapper(ProductoModelMapper.class);

    ProductoModel toDomain(CreateProductoRequest request);

    ProductoModel toDomain(UpdateProductoRequest request);

    ProductoResponse toResponse(ProductoModel model);  //mapeara solo lo que tenga el modelo
}
