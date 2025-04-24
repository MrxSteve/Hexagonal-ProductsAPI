package com.devsteve.product_mservice.shared.utils;

import com.devsteve.product_mservice.shared.exceptions.ImageUploadException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class ImagenUtils {
    private static final List<String> TIPOS_PERMITIDOS = List.of("image/jpeg", "image/png", "image/webp", "image/jpg");
    private static final long MAX_BYTES = 5 * 1024 * 1024; // 5 MB

    private ImagenUtils() {}

    // Valida el archivo subido
    public static void validarArchivo(MultipartFile archivo) {
        if (archivo == null || archivo.isEmpty()) {
            throw new ImageUploadException("No se proporcionó archivo");
        }
        String contentType = archivo.getContentType();
        if (contentType == null || !TIPOS_PERMITIDOS.contains(contentType)) {
            throw new ImageUploadException("Formato no permitido. Solo JPG, PNG o WEBP");
        }
        if (archivo.getSize() > MAX_BYTES) {
            throw new ImageUploadException("Archivo demasiado grande. Máximo 5MB");
        }
    }

    // Extrae el nombre de la imagen de la URL
    public static String extraerKey(String url) {
        if (url == null || !url.contains("/")) return url;
        return url.substring(url.lastIndexOf('/') + 1);
    }
}
