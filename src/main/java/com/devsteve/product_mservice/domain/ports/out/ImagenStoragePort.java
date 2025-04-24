package com.devsteve.product_mservice.domain.ports.out;

import org.springframework.web.multipart.MultipartFile;

// Interface a implementar en el S3
public interface ImagenStoragePort {
    String subirArchivo(String key, MultipartFile archivo);
    void eliminarArchivo(String nombre);
}
