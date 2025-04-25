package com.devsteve.product_mservice.infra.adapters.drivers.rest.controllers;

import com.devsteve.product_mservice.application.dto.res.ImagenResponse;
import com.devsteve.product_mservice.infra.adapters.driven.jpa.facade.ImagenTransactionalFacade;
import com.devsteve.product_mservice.infra.adapters.driven.jpa.mappers.images.ImagenModelMapper;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/api/imagenes")
@RequiredArgsConstructor
@Tag(name = "Imágenes", description = "Operaciones para subir, listar y eliminar imágenes de productos")
public class ImagenController {
    private final ImagenTransactionalFacade imagenTransactionalFacade;
    private final ImagenModelMapper imagenModelMapper;

    @Operation(summary = "Subir una imagen a un producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Imagen subida exitosamente"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @PostMapping(value = "/{productoId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ImagenResponse> subirImagen(
            @Parameter(description = "ID del producto al que se asignará la imagen", example = "1") @PathVariable Long productoId,
            @Parameter(description = "Archivo de imagen a subir") @RequestParam("file") MultipartFile file) {
        ImagenResponse response = imagenModelMapper
                .toResponse(imagenTransactionalFacade.subirImagen(productoId, file));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Listar todas las imágenes de un producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de imágenes obtenido"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @GetMapping("/producto/{productoId}")
    public ResponseEntity<List<ImagenResponse>> listarImagenes(
            @Parameter(description = "ID del producto", example = "1") @PathVariable Long productoId) {
        List<ImagenResponse> imagenes = imagenTransactionalFacade.listarImagenes(productoId)
                .stream()
                .map(imagenModelMapper::toResponse)
                .toList();
        return ResponseEntity.ok(imagenes);
    }

    @Operation(summary = "Eliminar una imagen por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Imagen eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Imagen no encontrada")
    })
    @DeleteMapping("/{imagenId}")
    public ResponseEntity<Void> eliminarImagen(
            @Parameter(description = "ID de la imagen", example = "10") @PathVariable Long imagenId) {
        imagenTransactionalFacade.eliminarImagenPorId(imagenId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Eliminar una imagen específica de un producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Imagen eliminada del producto"),
            @ApiResponse(responseCode = "404", description = "Producto o imagen no encontrados")
    })
    @DeleteMapping("/producto/{productoId}/imagen/{imagenId}")
    public ResponseEntity<Void> eliminarImagenDeProducto(
            @Parameter(description = "ID del producto", example = "1") @PathVariable Long productoId,
            @Parameter(description = "ID de la imagen", example = "10") @PathVariable Long imagenId) {
        imagenTransactionalFacade.eliminarImagenDeProducto(productoId, imagenId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Eliminar todas las imágenes de un producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Todas las imágenes eliminadas del producto"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @DeleteMapping("/producto/{productoId}")
    public ResponseEntity<Void> eliminarTodas(
            @Parameter(description = "ID del producto", example = "1") @PathVariable Long productoId) {
        imagenTransactionalFacade.eliminarTodasDeProducto(productoId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Buscar una imagen por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Imagen encontrada"),
            @ApiResponse(responseCode = "404", description = "Imagen no encontrada")
    })
    @GetMapping("/{imagenId}")
    public ResponseEntity<ImagenResponse> buscarImagen(
            @Parameter(description = "ID de la imagen", example = "10") @PathVariable Long imagenId) {
        ImagenResponse response = imagenModelMapper
                .toResponse(imagenTransactionalFacade.buscarImagen(imagenId));
        return ResponseEntity.ok(response);
    }
}

