package com.devsteve.product_mservice.application.services;

import com.devsteve.product_mservice.application.ports.in.categoria.BuscarCategoriaPorIdUseCase;
import com.devsteve.product_mservice.application.ports.in.marca.BuscarMarcaPorIdUseCase;
import com.devsteve.product_mservice.application.ports.in.producto.crud.*;
import com.devsteve.product_mservice.application.ports.in.producto.filters.*;
import com.devsteve.product_mservice.domain.model.ProductoModel;
import com.devsteve.product_mservice.domain.model.enums.EstadoProducto;
import com.devsteve.product_mservice.domain.ports.out.ProductoRepository;
import com.devsteve.product_mservice.shared.exceptions.DuplicateResourceException;
import com.devsteve.product_mservice.shared.exceptions.MultipleErrorsException;
import com.devsteve.product_mservice.shared.exceptions.ResourceNotFoundException;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private final BuscarMarcaPorIdUseCase buscarMarcaPorIdUseCase;
    private final BuscarCategoriaPorIdUseCase buscarCategoriaPorIdUseCase;

    public ProductoService(ProductoRepository productoRepository,
                           BuscarMarcaPorIdUseCase buscarMarcaPorIdUseCase,
                           BuscarCategoriaPorIdUseCase buscarCategoriaPorIdUseCase) {
        this.productoRepository = productoRepository;
        this.buscarMarcaPorIdUseCase = buscarMarcaPorIdUseCase;
        this.buscarCategoriaPorIdUseCase = buscarCategoriaPorIdUseCase;
    }

    @Override
    public ProductoModel crearProducto(ProductoModel productoModel) {
        List<String> errores = new ArrayList<>();

        if (productoRepository.existePorNombre(productoModel.getNombre())) {
            errores.add("Ya existe un producto con el nombre " + productoModel.getNombre());
        }

        try {
            buscarMarcaPorIdUseCase.buscarPorId(productoModel.getMarcaId());
        } catch (ResourceNotFoundException e) {
            errores.add("Marca no encontrada con id: " + productoModel.getMarcaId());
        }

        try {
            buscarCategoriaPorIdUseCase.buscarPorId(productoModel.getCategoriaId());
        } catch (ResourceNotFoundException e) {
            errores.add("Categoria no encontrada con id: " + productoModel.getCategoriaId());
        }

        if (!errores.isEmpty()) {
            throw new MultipleErrorsException(errores);
        }

        productoModel.setEstado(EstadoProducto.ACTIVO);
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
        List<String> errores = new ArrayList<>();

        if (model.getNombre() != null &&
                productoRepository.existePorNombre(model.getNombre()) &&
                !existente.getNombre().equalsIgnoreCase(model.getNombre())) {
            errores.add("Ya existe un producto con el nombre " + model.getNombre());
        }

        if (model.getMarcaId() != null) {
            try {
                buscarMarcaPorIdUseCase.buscarPorId(model.getMarcaId());
            } catch (ResourceNotFoundException e) {
                errores.add("Marca no encontrada con id: " + model.getMarcaId());
            }
        }

        if (model.getCategoriaId() != null) {
            try {
                buscarCategoriaPorIdUseCase.buscarPorId(model.getCategoriaId());
            } catch (ResourceNotFoundException e) {
                errores.add("Categoria no encontrada con id: " + model.getCategoriaId());
            }
        }

        if (!errores.isEmpty()) {
            throw new MultipleErrorsException(errores);
        }

        // Actualizar solo los campos no nulos
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
