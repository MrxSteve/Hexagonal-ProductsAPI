package com.devsteve.product_mservice.infra.adapters.driven.jpa.mappers.categoria;

import com.devsteve.product_mservice.infra.adapters.driven.jpa.entities.CategoriaEntity;
import com.devsteve.product_mservice.domain.model.CategoriaModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoriaEntityMapper {
    CategoriaEntity toEntity(CategoriaModel model);
    CategoriaModel toDomain(CategoriaEntity entity);
}
