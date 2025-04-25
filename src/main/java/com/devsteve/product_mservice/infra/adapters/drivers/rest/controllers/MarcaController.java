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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;

import java.util.List;
@RestController
@RequestMapping("/api/marcas")
@RequiredArgsConstructor
@Tag(name = "Marcas", description = "CRUD de marcas de productos")
public class MarcaController {

    private final CrearMarcaUseCase crearMarcaUseCase;
    private final ListarMarcasUseCase listarMarcasUseCase;
    private final BuscarMarcaPorIdUseCase buscarMarcaPorIdUseCase;
    private final ActualizarMarcaUseCase actualizarMarcaUseCase;
    private final EliminarMarcaUseCase eliminarMarcaUseCase;
    private final BuscarMarcaPorNombreUseCase buscarMarcaPorNombreUseCase;
    private final MarcaModelMapper marcaModelMapper;

    @Operation(summary = "Crear una nueva marca")
    @PostMapping
    public ResponseEntity<MarcaResponse> crearMarca(@RequestBody @Valid MarcaRequest request) {
        MarcaModel marca = marcaModelMapper.toDomain(request);
        MarcaModel marcaCreada = crearMarcaUseCase.crearMarca(marca);
        return ResponseEntity.ok(marcaModelMapper.toResponse(marcaCreada));
    }

    @Operation(summary = "Listar todas las marcas paginadas")
    @GetMapping
    public ResponseEntity<List<MarcaResponse>> listarMarcas(
            @Parameter(description = "Número de página", example = "0") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Tamaño de página", example = "10") @RequestParam(defaultValue = "10") int size) {
        List<MarcaModel> marcas = listarMarcasUseCase.listarMarcas(page, size);
        List<MarcaResponse> marcaResponses = marcas.stream()
                .map(marcaModelMapper::toResponse)
                .toList();
        return ResponseEntity.ok(marcaResponses);
    }

    @Operation(summary = "Buscar marca por ID")
    @GetMapping("/{id}")
    public ResponseEntity<MarcaResponse> buscarMarcaPorId(
            @Parameter(description = "ID de la marca", example = "1") @PathVariable Long id) {
        MarcaModel marca = buscarMarcaPorIdUseCase.buscarPorId(id);
        return ResponseEntity.ok(marcaModelMapper.toResponse(marca));
    }

    @Operation(summary = "Actualizar una marca por ID")
    @PutMapping("/{id}")
    public ResponseEntity<MarcaResponse> actualizarMarca(
            @Parameter(description = "ID de la marca a actualizar", example = "1") @PathVariable Long id,
            @RequestBody @Valid MarcaRequest request) {
        MarcaModel marca = marcaModelMapper.toDomain(request);
        MarcaModel marcaActualizada = actualizarMarcaUseCase.actualizar(id, marca);
        return ResponseEntity.ok(marcaModelMapper.toResponse(marcaActualizada));
    }

    @Operation(summary = "Eliminar una marca por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMarca(
            @Parameter(description = "ID de la marca a eliminar", example = "1") @PathVariable Long id) {
        eliminarMarcaUseCase.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Buscar marcas por nombre (paginado)")
    @GetMapping("/nombre")
    public ResponseEntity<List<MarcaResponse>> buscarMarcaPorNombre(
            @Parameter(description = "Nombre de la marca a buscar", example = "Logitech") @RequestParam String nombre,
            @Parameter(description = "Número de página", example = "0") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Tamaño de página", example = "10") @RequestParam(defaultValue = "10") int size) {
        List<MarcaModel> marcas = buscarMarcaPorNombreUseCase.buscarPorNombre(nombre, page, size);
        List<MarcaResponse> marcaResponses = marcas.stream()
                .map(marcaModelMapper::toResponse)
                .toList();
        return ResponseEntity.ok(marcaResponses);
    }
}
