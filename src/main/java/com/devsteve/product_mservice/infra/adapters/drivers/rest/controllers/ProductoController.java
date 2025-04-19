package com.devsteve.product_mservice.infra.adapters.drivers.rest.controllers;

import com.devsteve.product_mservice.application.dto.req.CreateProductoRequest;
import com.devsteve.product_mservice.application.dto.req.UpdateProductoRequest;
import com.devsteve.product_mservice.application.dto.res.ProductoResponse;
import com.devsteve.product_mservice.application.ports.in.categoria.ObtenerNombreCategoriaUseCase;
import com.devsteve.product_mservice.application.ports.in.marca.ObtenerNombreMarcaUseCase;
import com.devsteve.product_mservice.application.ports.in.producto.crud.*;
import com.devsteve.product_mservice.application.ports.in.producto.filters.BuscarProductosPorFiltrosUseCase;
import com.devsteve.product_mservice.domain.model.ProductoModel;
import com.devsteve.product_mservice.domain.model.enums.EstadoProducto;
import com.devsteve.product_mservice.infra.adapters.driven.jpa.mappers.producto.ProductoModelMapper;
import com.devsteve.product_mservice.infra.adapters.drivers.rest.assembler.ProductoResponseAssembler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController {
    private final CrearProductoUseCase crearProductoUseCase;
    private final ActualizarProductoUseCase actualizarProductoUseCase;
    private final EliminarProductoUseCase eliminarProductoUseCase;
    private final BuscarProductoPorIdUseCase buscarProductoPorIdUseCase;
    private final ListarProductosUseCase listarProductosUseCase;
    private final BuscarProductosPorFiltrosUseCase buscarProductosPorFiltrosUseCase;
    private final ProductoModelMapper productoModelMapper;

    // Servicios auxiliares para obtener nombres
    private final ObtenerNombreMarcaUseCase obtenerNombreMarcaUseCase;
    private final ObtenerNombreCategoriaUseCase obtenerNombreCategoriaUseCase;

    @PostMapping
    public ResponseEntity<ProductoResponse> crearProducto(
            @Valid @RequestBody CreateProductoRequest request) {

        ProductoModel model = productoModelMapper.toDomain(request);
        ProductoModel creado = crearProductoUseCase.crearProducto(model);
        return ResponseEntity.ok(mapearProductoConNombres(creado));
    }

    @GetMapping
    public ResponseEntity<List<ProductoResponse>> listarProductos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        List<ProductoModel> productos = listarProductosUseCase.listarProductos(page, size);
        List<ProductoResponse> responses = productos.stream()
                .map(this::mapearProductoConNombres)
                .toList();

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponse> buscarPorId(@PathVariable Long id) {
        ProductoModel producto = buscarProductoPorIdUseCase.buscarPorId(id);
        return ResponseEntity.ok(mapearProductoConNombres(producto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponse> actualizarProducto(
            @PathVariable Long id,
            @Valid @RequestBody UpdateProductoRequest request
    ) {
        ProductoModel updateModel = productoModelMapper.toDomain(request);
        ProductoModel actualizado = actualizarProductoUseCase.actualizarProducto(id, updateModel);
        return ResponseEntity.ok(mapearProductoConNombres(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        eliminarProductoUseCase.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filtrar")
    public ResponseEntity<List<ProductoResponse>> buscarPorFiltros(
            @RequestParam Optional<String> nombre,
            @RequestParam Optional<Long> marcaId,
            @RequestParam Optional<Long> categoriaId,
            @RequestParam Optional<EstadoProducto> estado,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
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
        return ProductoResponseAssembler.toResponse(producto, marcaNombre, categoriaNombre);
    }
}
