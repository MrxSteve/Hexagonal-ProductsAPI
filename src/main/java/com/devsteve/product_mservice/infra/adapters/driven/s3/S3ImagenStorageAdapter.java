package com.devsteve.product_mservice.infra.adapters.driven.s3;

import com.devsteve.product_mservice.domain.ports.out.ImagenStoragePort;
import com.devsteve.product_mservice.shared.exceptions.ImageUploadException;
import com.devsteve.product_mservice.shared.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.NoSuchKeyException;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class S3ImagenStorageAdapter implements ImagenStoragePort {
    private final S3Client s3Client;

    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    @Value("${aws.region}")
    private String region;

    @Override
    public String subirArchivo(String key, MultipartFile archivo) {
        try {
            PutObjectRequest request = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .contentType(archivo.getContentType())
                    .build();

            s3Client.putObject(request, software.amazon.awssdk.core.sync.RequestBody.fromBytes(archivo.getBytes()));

            // URL publica generada manualmente
            return "https://" + bucketName + ".s3." + region + ".amazonaws.com/" + key;

        } catch (IOException e) {
            throw new ImageUploadException("Error al subir archivo a S3", e);
        }
    }

    @Override
    public void eliminarArchivo(String key) {
        if (existeArchivo(key)) {
            DeleteObjectRequest request = DeleteObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .build();

            s3Client.deleteObject(request);
        } else {
            throw new ResourceNotFoundException("El archivo con la clave " + key + " no existe en S3");
        }
    }

    // Metodo para verificar si el archivo existe
    private boolean existeArchivo(String key) {
        try {
            s3Client.headObject(builder -> builder.bucket(bucketName).key(key));
            return true; // El archivo existe
        } catch (NoSuchKeyException e) {
            return false; // No existe
        } catch (Exception e) {
            throw new ImageUploadException("Error al verificar existencia del archivo en S3", e);
        }
    }
}
