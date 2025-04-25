package com.devsteve.product_mservice.infra.adapters.drivers.rest.controllers;

import com.devsteve.product_mservice.application.ports.in.categoria.*;
import com.devsteve.product_mservice.infra.adapters.driven.jpa.mappers.categoria.CategoriaModelMapper;
import com.devsteve.product_mservice.application.dto.req.CategoriaRequest;
import com.devsteve.product_mservice.application.dto.res.CategoriaResponse;
import com.devsteve.product_mservice.domain.model.CategoriaModel;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
@Tag(name = "Categorias", description = "CRUD de categorías de productos")
public class CategoriaController {
    private final CrearCategoriaUseCase crearCategoriaUseCase;
    private final ListarCategoriasUseCase listarCategoriasUseCase;
    private final BuscarCategoriaPorIdUseCase buscarCategoriaPorIdUseCase;
    private final ActualizarCategoriaUseCase actualizarCategoriaUseCase;
    private final EliminarCategoriaUseCase eliminarCategoriaUseCase;
    private final BuscarCategoriaPorNombreUseCase buscarCategoriaPorNombreUseCase;
    private final CategoriaModelMapper categoriaModelMapper;

    @Operation(summary = "Crear una nueva categoría")
    @PostMapping
    public ResponseEntity<CategoriaResponse> crearCategoria(@RequestBody @Valid CategoriaRequest request) {
        CategoriaModel categoria = categoriaModelMapper.toDomain(request);
        CategoriaModel categoriaCreada = crearCategoriaUseCase.crearCategoria(categoria);
        return ResponseEntity.ok(categoriaModelMapper.toResponse(categoriaCreada));
    }

    @Operation(summary = "Listar todas las categorías paginadas")
    @GetMapping
    public ResponseEntity<List<CategoriaResponse>> listarCategorias(
            @Parameter(description = "Número de página", example = "0") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Tamaño de página", example = "10") @RequestParam(defaultValue = "10") int size) {
        List<CategoriaModel> categorias = listarCategoriasUseCase.listarCategorias(page, size);
        List<CategoriaResponse> categoriaResponses = categorias.stream()
                .map(categoriaModelMapper::toResponse)
                .toList();
        return ResponseEntity.ok(categoriaResponses);
    }

    @Operation(summary = "Buscar categoría por ID")
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponse> buscarCategoriaPorId(
            @Parameter(description = "ID de la categoría", example = "1") @PathVariable Long id) {
        CategoriaModel categoria = buscarCategoriaPorIdUseCase.buscarPorId(id);
        return ResponseEntity.ok(categoriaModelMapper.toResponse(categoria));
    }

    @Operation(summary = "Actualizar una categoría por ID")
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponse> actualizarCategoria(
            @Parameter(description = "ID de la categoría a actualizar", example = "1") @PathVariable Long id,
            @RequestBody @Valid CategoriaRequest request) {
        CategoriaModel categoria = categoriaModelMapper.toDomain(request);
        CategoriaModel categoriaActualizada = actualizarCategoriaUseCase.actualizar(id, categoria);
        return ResponseEntity.ok(categoriaModelMapper.toResponse(categoriaActualizada));
    }

    @Operation(summary = "Eliminar una categoría por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCategoria(
            @Parameter(description = "ID de la categoría a eliminar", example = "1") @PathVariable Long id) {
        eliminarCategoriaUseCase.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Buscar categorías por nombre (paginado)")
    @GetMapping("/nombre")
    public ResponseEntity<List<CategoriaResponse>> buscarPorNombre(
            @Parameter(description = "Nombre a buscar", example = "Gaming") @RequestParam String nombre,
            @Parameter(description = "Número de página", example = "0") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Tamaño de página", example = "10") @RequestParam(defaultValue = "10") int size) {
        List<CategoriaModel> categorias = buscarCategoriaPorNombreUseCase.buscarPorNombre(nombre, page, size);
        List<CategoriaResponse> categoriaResponses = categorias.stream()
                .map(categoriaModelMapper::toResponse)
                .toList();
        return ResponseEntity.ok(categoriaResponses);
    }
}
