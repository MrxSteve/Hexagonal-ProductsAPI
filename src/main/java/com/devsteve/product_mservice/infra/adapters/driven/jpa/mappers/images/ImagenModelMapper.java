package com.devsteve.product_mservice.infra.adapters.driven.jpa.mappers.images;

import com.devsteve.product_mservice.application.dto.res.ImagenResponse;
import com.devsteve.product_mservice.domain.model.ImagenModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImagenModelMapper {
    ImagenResponse toResponse(ImagenModel model);
}
