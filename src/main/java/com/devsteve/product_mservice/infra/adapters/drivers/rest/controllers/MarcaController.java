package com.devsteve.product_mservice.infra.adapters.drivers.rest.controllers;

import com.devsteve.product_mservice.application.dto.req.MarcaRequest;
import com.devsteve.product_mservice.application.dto.res.MarcaResponse;
import com.devsteve.product_mservice.application.ports.in.marca.*;
import com.devsteve.product_mservice.domain.model.MarcaModel;
import com.devsteve.product_mservice.infra.adapters.driven.jpa.mappers.marca.MarcaModelMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/marcas")
@RequiredArgsConstructor
public class MarcaController {
    private final CrearMarcaUseCase crearMarcaUseCase;
    private final ListarMarcasUseCase listarMarcasUseCase;
    private final BuscarMarcaPorIdUseCase buscarMarcaPorIdUseCase;
    private final ActualizarMarcaUseCase actualizarMarcaUseCase;
    private final EliminarMarcaUseCase eliminarMarcaUseCase;
    private final BuscarMarcaPorNombreUseCase buscarMarcaPorNombreUseCase;
    private final MarcaModelMapper marcaModelMapper;

    @PostMapping
    public ResponseEntity<MarcaResponse> crearMarca(
            @RequestBody @Valid MarcaRequest request) {
        MarcaModel marca = marcaModelMapper.toDomain(request);
        MarcaModel marcaCreada = crearMarcaUseCase.crearMarca(marca);

        return ResponseEntity.ok(marcaModelMapper.toResponse(marcaCreada));
    }

    @GetMapping
    public ResponseEntity<List<MarcaResponse>> listarMarcas(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<MarcaModel> marcas = listarMarcasUseCase.listarMarcas(page, size);
        List<MarcaResponse> marcaResponses = marcas.stream()
                .map(marcaModelMapper::toResponse)
                .toList();

        return ResponseEntity.ok(marcaResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MarcaResponse> buscarMarcaPorId(@PathVariable Long id) {
        MarcaModel marca = buscarMarcaPorIdUseCase.buscarPorId(id);
        MarcaResponse marcaResponse = marcaModelMapper.toResponse(marca);

        return ResponseEntity.ok(marcaResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MarcaResponse> actualizarMarca(
            @PathVariable Long id,
            @RequestBody @Valid MarcaRequest request) {
        MarcaModel marca = marcaModelMapper.toDomain(request);
        MarcaModel marcaActualizada = actualizarMarcaUseCase.actualizar(id, marca);
        MarcaResponse marcaResponse = marcaModelMapper.toResponse(marcaActualizada);

        return ResponseEntity.ok(marcaResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMarca(@PathVariable Long id) {
        eliminarMarcaUseCase.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/nombre")
    public ResponseEntity<List<MarcaResponse>> buscarMarcaPorNombre(
            @RequestParam String nombre,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<MarcaModel> marcas = buscarMarcaPorNombreUseCase.buscarPorNombre(nombre, page, size);
        List<MarcaResponse> marcaResponses = marcas.stream()
                .map(marcaModelMapper::toResponse)
                .toList();

        return ResponseEntity.ok(marcaResponses);
    }
}
