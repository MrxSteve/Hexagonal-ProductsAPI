package com.devsteve.product_mservice.infra.adapters.driven.jpa.mappers.producto;

import com.devsteve.product_mservice.domain.model.ProductoModel;
import com.devsteve.product_mservice.infra.adapters.driven.jpa.entities.ProductoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductoEntityMapper {
    ProductoEntity toEntity(ProductoModel model);
    ProductoModel toDomain(ProductoEntity entity);
}
