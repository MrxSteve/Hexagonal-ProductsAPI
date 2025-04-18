package com.devsteve.product_mservice.infra.adapters.drivers.rest;

import com.devsteve.product_mservice.application.ports.in.categoria.*;
import com.devsteve.product_mservice.infra.adapters.driven.jpa.mappers.categoria.CategoriaModelMapper;
import com.devsteve.product_mservice.application.dto.req.CategoriaRequest;
import com.devsteve.product_mservice.application.dto.res.CategoriaResponse;
import com.devsteve.product_mservice.domain.model.CategoriaModel;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
public class CategoriaController {
    private final CrearCategoriaUseCase crearCategoriaUseCase;
    private final ListarCategoriasUseCase listarCategoriasUseCase;
    private final BuscarCategoriaPorIdUseCase buscarCategoriaPorIdUseCase;
    private final ActualizarCategoriaUseCase actualizarCategoriaUseCase;
    private final EliminarCategoriaUseCase eliminarCategoriaUseCase;
    private final BuscarCategoriaPorNombreUseCase buscarCategoriaPorNombreUseCase;
    private final CategoriaModelMapper categoriaModelMapper;

    @PostMapping
    public ResponseEntity<CategoriaResponse> crearCategoria(
            @RequestBody @Valid CategoriaRequest request) {
        CategoriaModel categoria = categoriaModelMapper.toDomain(request);
        CategoriaModel categoriaCreada = crearCategoriaUseCase.crearCategoria(categoria);

        return ResponseEntity.ok(categoriaModelMapper.toResponse(categoriaCreada));
    }

    @GetMapping
    public ResponseEntity<List<CategoriaResponse>> listarCategorias(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<CategoriaModel> categorias = listarCategoriasUseCase.listarCategorias(page, size);
        List<CategoriaResponse> categoriaResponses = categorias.stream()
                .map(categoriaModelMapper::toResponse)
                .toList();

        return ResponseEntity.ok(categoriaResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponse> buscarCategoriaPorId(@PathVariable Long id) {
        CategoriaModel categoria = buscarCategoriaPorIdUseCase.buscarPorId(id);
        CategoriaResponse categoriaResponse = categoriaModelMapper.toResponse(categoria);

        return ResponseEntity.ok(categoriaResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponse> actualizarCategoria(
            @PathVariable Long id,
            @RequestBody @Valid CategoriaRequest request) {
        CategoriaModel categoria = categoriaModelMapper.toDomain(request);
        CategoriaModel categoriaActualizada = actualizarCategoriaUseCase.actualizar(id, categoria);
        CategoriaResponse categoriaResponse = categoriaModelMapper.toResponse(categoriaActualizada);

        return ResponseEntity.ok(categoriaResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCategoria(@PathVariable Long id) {
        eliminarCategoriaUseCase.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/nombre")
    public ResponseEntity<List<CategoriaResponse>> buscarPorNombre(
            @RequestParam String nombre,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        List<CategoriaModel> categorias = buscarCategoriaPorNombreUseCase.buscarPorNombre(nombre, page, size);
        List<CategoriaResponse> categoriaResponses = categorias.stream()
                .map(categoriaModelMapper::toResponse)
                .toList();

        return ResponseEntity.ok(categoriaResponses);
    }

}
