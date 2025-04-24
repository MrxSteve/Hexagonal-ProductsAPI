package com.devsteve.product_mservice.infra.adapters.drivers.rest.controllers;

import com.devsteve.product_mservice.application.dto.res.ImagenResponse;
import com.devsteve.product_mservice.infra.adapters.driven.jpa.facade.ImagenTransactionalFacade;
import com.devsteve.product_mservice.infra.adapters.driven.jpa.mappers.images.ImagenModelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/imagenes")
@RequiredArgsConstructor
public class ImagenController {
    private final ImagenTransactionalFacade imagenTransactionalFacade;
    private final ImagenModelMapper imagenModelMapper;

    @PostMapping(value = "/{productoId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ImagenResponse> subirImagen(
            @PathVariable Long productoId,
            @RequestParam("file") MultipartFile file) {
        ImagenResponse response = imagenModelMapper
                .toResponse(imagenTransactionalFacade.subirImagen(productoId, file));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/producto/{productoId}")
    public ResponseEntity<List<ImagenResponse>> listarImagenes(@PathVariable Long productoId) {
        List<ImagenResponse> imagenes = imagenTransactionalFacade.listarImagenes(productoId)
                .stream()
                .map(imagenModelMapper::toResponse)
                .toList();
        return ResponseEntity.ok(imagenes);
    }

    @DeleteMapping("/{imagenId}")
    public ResponseEntity<Void> eliminarImagen(@PathVariable Long imagenId) {
        imagenTransactionalFacade.eliminarImagenPorId(imagenId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/producto/{productoId}/imagen/{imagenId}")
    public ResponseEntity<Void> eliminarImagenDeProducto(@PathVariable Long productoId,
                                                         @PathVariable Long imagenId) {
        imagenTransactionalFacade.eliminarImagenDeProducto(productoId, imagenId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/producto/{productoId}")
    public ResponseEntity<Void> eliminarTodas(@PathVariable Long productoId) {
        imagenTransactionalFacade.eliminarTodasDeProducto(productoId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{imagenId}")
    public ResponseEntity<ImagenResponse> buscarImagen(@PathVariable Long imagenId) {
        ImagenResponse response = imagenModelMapper
                .toResponse(imagenTransactionalFacade.buscarImagen(imagenId));
        return ResponseEntity.ok(response);
    }
}
