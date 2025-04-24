package com.devsteve.product_mservice.application.ports.in.images;

import com.devsteve.product_mservice.domain.model.ImagenModel;
import org.springframework.web.multipart.MultipartFile;

public interface SubirImagenUseCase {
    ImagenModel subirImagen(Long productoId, MultipartFile file);
}
