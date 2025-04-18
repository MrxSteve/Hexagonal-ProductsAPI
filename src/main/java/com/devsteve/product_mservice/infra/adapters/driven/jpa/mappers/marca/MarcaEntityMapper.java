package com.devsteve.product_mservice.infra.adapters.driven.jpa.mappers.marca;

import com.devsteve.product_mservice.domain.model.MarcaModel;
import com.devsteve.product_mservice.infra.adapters.driven.jpa.entities.MarcaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MarcaEntityMapper {
    MarcaEntity toEntity(MarcaModel model);
    MarcaModel toDomain(MarcaEntity entity);
}
