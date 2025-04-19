package com.devsteve.product_mservice.application.services;

import com.devsteve.product_mservice.application.ports.in.producto.crud.*;
import com.devsteve.product_mservice.application.ports.in.producto.filters.*;
import com.devsteve.product_mservice.domain.model.ProductoModel;
import com.devsteve.product_mservice.domain.model.enums.EstadoProducto;
import com.devsteve.product_mservice.domain.ports.out.ProductoRepository;
import com.devsteve.product_mservice.shared.exceptions.DuplicateResourceException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class ProductoService implements
        CrearProductoUseCase,
        ListarProductosUseCase,
        BuscarProductoPorIdUseCase,
        ActualizarProductoUseCase,
        EliminarProductoUseCase,
        BuscarProductosPorFiltrosUseCase {
    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public ProductoModel crearProducto(ProductoModel productoModel) {
        if (productoRepository.existePorNombre(productoModel.getNombre())) {
            throw new DuplicateResourceException("Ya existe un producto con el nombre " + productoModel.getNombre());
        }

        // Por defecto el producto inicia como ACTIVO
        productoModel.setEstado(EstadoProducto.ACTIVO);

        // Establecer la fecha
        productoModel.setFechaCreacion(LocalDateTime.now());

        return productoRepository.guardar(productoModel);
    }

    @Override
    public List<ProductoModel> listarProductos(int page, int size) {
        return productoRepository.buscarTodas(page, size);
    }

    @Override
    public ProductoModel buscarPorId(Long id) {
        return productoRepository.buscarPorId(id);
    }

    @Override
    public ProductoModel actualizarProducto(Long id, ProductoModel model) {
        ProductoModel existente = buscarPorId(id);

        if (model.getNombre() != null &&
                productoRepository.existePorNombre(model.getNombre()) &&
                !existente.getNombre().equalsIgnoreCase(model.getNombre())) {
            throw new DuplicateResourceException("Ya existe un producto con el nombre " + model.getNombre());
        }

        if (model.getNombre() != null) {
            existente.setNombre(model.getNombre());
        }

        if (model.getDescripcion() != null) {
            existente.setDescripcion(model.getDescripcion());
        }

        if (model.getPrecio() != null) {
            existente.setPrecio(model.getPrecio());
        }

        if (model.getMarcaId() != null) {
            existente.setMarcaId(model.getMarcaId());
        }

        if (model.getCategoriaId() != null) {
            existente.setCategoriaId(model.getCategoriaId());
        }

        if (model.getEstado() != null) {
            existente.setEstado(model.getEstado());
        }

        return productoRepository.guardar(existente);
    }

    @Override
    public void eliminarProducto(Long id) {
        buscarPorId(id);
        productoRepository.eliminar(id);
    }

    @Override
    public List<ProductoModel> buscarConFiltros(Optional<String> nombre,
                                                Optional<Long> marcaId,
                                                Optional<Long> categoriaId,
                                                Optional<EstadoProducto> estado,
                                                int page, int size) {
        return productoRepository.buscarConFiltros(nombre, marcaId, categoriaId, estado, page, size);
    }
}
