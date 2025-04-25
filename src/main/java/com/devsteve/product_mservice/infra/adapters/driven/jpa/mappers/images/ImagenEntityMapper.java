package com.devsteve.product_mservice.infra.adapters.driven.jpa.mappers.images;

import com.devsteve.product_mservice.domain.model.ImagenModel;
import com.devsteve.product_mservice.infra.adapters.driven.jpa.entities.ImagenEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ImagenEntityMapper {
    @Mapping(target = "productoId", source = "productoEntity.id")
    ImagenModel toDomain(ImagenEntity entity);

    @Mapping(target = "productoEntity.id", source = "productoId")
    ImagenEntity toEntity(ImagenModel model);
}
