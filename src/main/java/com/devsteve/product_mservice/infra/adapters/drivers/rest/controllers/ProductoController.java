package com.devsteve.product_mservice.infra.adapters.drivers.rest.controllers;

import com.devsteve.product_mservice.application.dto.req.CreateProductoRequest;
import com.devsteve.product_mservice.application.dto.req.UpdateProductoRequest;
import com.devsteve.product_mservice.application.dto.res.ImagenResponse;
import com.devsteve.product_mservice.application.dto.res.ProductoResponse;
import com.devsteve.product_mservice.application.ports.in.categoria.ObtenerNombreCategoriaUseCase;
import com.devsteve.product_mservice.application.ports.in.images.ListarImagenesPorProductoUseCase;
import com.devsteve.product_mservice.application.ports.in.marca.ObtenerNombreMarcaUseCase;
import com.devsteve.product_mservice.application.ports.in.producto.crud.*;
import com.devsteve.product_mservice.application.ports.in.producto.filters.BuscarProductosPorFiltrosUseCase;
import com.devsteve.product_mservice.domain.model.ProductoModel;
import com.devsteve.product_mservice.domain.model.enums.EstadoProducto;
import com.devsteve.product_mservice.infra.adapters.driven.jpa.mappers.images.ImagenModelMapper;
import com.devsteve.product_mservice.infra.adapters.driven.jpa.mappers.producto.ProductoModelMapper;
import com.devsteve.product_mservice.infra.adapters.drivers.rest.assembler.ProductoResponseAssembler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
@Tag(name = "Productos", description = "CRUD de productos, con imágenes, marcas y categorías")
public class ProductoController {
    private final CrearProductoUseCase crearProductoUseCase;
    private final ActualizarProductoUseCase actualizarProductoUseCase;
    private final EliminarProductoUseCase eliminarProductoUseCase;
    private final BuscarProductoPorIdUseCase buscarProductoPorIdUseCase;
    private final ListarProductosUseCase listarProductosUseCase;
    private final BuscarProductosPorFiltrosUseCase buscarProductosPorFiltrosUseCase;
    private final ProductoModelMapper productoModelMapper;

    // Servicio para listar imagenes por producto
    private final ListarImagenesPorProductoUseCase listarImagenesPorProductoUseCase;
    private final ImagenModelMapper imagenModelMapper;

    // Servicios auxiliares para obtener nombres
    private final ObtenerNombreMarcaUseCase obtenerNombreMarcaUseCase;
    private final ObtenerNombreCategoriaUseCase obtenerNombreCategoriaUseCase;

    @Operation(summary = "Crear un nuevo producto")
    @PostMapping
    public ResponseEntity<ProductoResponse> crearProducto(
            @Valid @RequestBody CreateProductoRequest request) {
        ProductoModel model = productoModelMapper.toDomain(request);
        ProductoModel creado = crearProductoUseCase.crearProducto(model);
        return ResponseEntity.ok(mapearProductoConNombres(creado));
    }

    @Operation(summary = "Listar todos los productos paginados")
    @GetMapping
    public ResponseEntity<List<ProductoResponse>> listarProductos(
            @Parameter(description = "Número de página", example = "0") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Tamaño de página", example = "10") @RequestParam(defaultValue = "10") int size) {
        List<ProductoModel> productos = listarProductosUseCase.listarProductos(page, size);
        List<ProductoResponse> responses = productos.stream()
                .map(this::mapearProductoConNombres)
                .toList();
        return ResponseEntity.ok(responses);
    }

    @Operation(summary = "Buscar un producto por ID")
    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponse> buscarPorId(
            @Parameter(description = "ID del producto", example = "1") @PathVariable Long id) {
        ProductoModel producto = buscarProductoPorIdUseCase.buscarPorId(id);
        return ResponseEntity.ok(mapearProductoConNombres(producto));
    }

    @Operation(summary = "Actualizar un producto por ID")
    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponse> actualizarProducto(
            @Parameter(description = "ID del producto a actualizar", example = "1") @PathVariable Long id,
            @Valid @RequestBody UpdateProductoRequest request) {
        ProductoModel updateModel = productoModelMapper.toDomain(request);
        ProductoModel actualizado = actualizarProductoUseCase.actualizarProducto(id, updateModel);
        return ResponseEntity.ok(mapearProductoConNombres(actualizado));
    }

    @Operation(summary = "Eliminar un producto por ID (incluye imágenes asociadas)")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(
            @Parameter(description = "ID del producto a eliminar", example = "1") @PathVariable Long id) {
        eliminarProductoUseCase.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Buscar productos por filtros (nombre, marca, categoría, estado)")
    @GetMapping("/filtrar")
    public ResponseEntity<List<ProductoResponse>> buscarPorFiltros(
            @Parameter(description = "Nombre del producto") @RequestParam Optional<String> nombre,
            @Parameter(description = "ID de la marca") @RequestParam Optional<Long> marcaId,
            @Parameter(description = "ID de la categoría") @RequestParam Optional<Long> categoriaId,
            @Parameter(description = "Estado del producto") @RequestParam Optional<EstadoProducto> estado,
            @Parameter(description = "Número de página", example = "0") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Tamaño de página", example = "10") @RequestParam(defaultValue = "10") int size) {
        List<ProductoModel> productos = buscarProductosPorFiltrosUseCase
                .buscarConFiltros(nombre, marcaId, categoriaId, estado, page, size);

        List<ProductoResponse> responses = productos.stream()
                .map(this::mapearProductoConNombres)
                .toList();
        return ResponseEntity.ok(responses);
    }

    //  Metodo reutilizable para mapear modelo + nombres
    private ProductoResponse mapearProductoConNombres(ProductoModel producto) {
        String marcaNombre = obtenerNombreMarcaUseCase.obtenerNombrePorId(producto.getMarcaId());
        String categoriaNombre = obtenerNombreCategoriaUseCase.obtenerNombrePorId(producto.getCategoriaId());

        // Buscar imagenes asociadas
        List<ImagenResponse> imagenes = listarImagenesPorProductoUseCase.listarImagenes(producto.getId())
                .stream()
                .map(imagenModelMapper::toResponse)
                .toList();

        return ProductoResponseAssembler.toResponse(producto, marcaNombre, categoriaNombre, imagenes);
    }
}
