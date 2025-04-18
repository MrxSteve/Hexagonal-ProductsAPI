package com.devsteve.product_mservice.infra.adapters.driven.jpa.mappers.categoria;

import com.devsteve.product_mservice.application.dto.req.CategoriaRequest;
import com.devsteve.product_mservice.application.dto.res.CategoriaResponse;
import com.devsteve.product_mservice.domain.model.CategoriaModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoriaModelMapper {
    CategoriaModelMapper INSTANCE = Mappers.getMapper(CategoriaModelMapper.class);

    CategoriaModel toDomain(CategoriaRequest request);
    CategoriaResponse toResponse(CategoriaModel model);
}
