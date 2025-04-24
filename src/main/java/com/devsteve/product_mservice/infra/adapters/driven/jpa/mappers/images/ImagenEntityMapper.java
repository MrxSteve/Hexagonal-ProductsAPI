package com.devsteve.product_mservice.infra.adapters.driven.jpa.mappers.images;

import com.devsteve.product_mservice.domain.model.ImagenModel;
import com.devsteve.product_mservice.infra.adapters.driven.jpa.entities.ImagenEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImagenEntityMapper {
    ImagenModel toDomain(ImagenEntity entity);
    ImagenEntity toEntity(ImagenModel model);
}
