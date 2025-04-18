package com.devsteve.product_mservice.infra.adapters.driven.jpa.mappers.marca;

import com.devsteve.product_mservice.application.dto.req.MarcaRequest;
import com.devsteve.product_mservice.application.dto.res.MarcaResponse;
import com.devsteve.product_mservice.domain.model.MarcaModel;
import com.devsteve.product_mservice.infra.adapters.driven.jpa.mappers.categoria.CategoriaModelMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MarcaModelMapper {
    CategoriaModelMapper INSTANCE = Mappers.getMapper(CategoriaModelMapper.class);

    MarcaModel toDomain(MarcaRequest request);
    MarcaResponse toResponse(MarcaModel model);
}
