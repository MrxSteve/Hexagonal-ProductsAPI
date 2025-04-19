package com.devsteve.product_mservice.application.services;

import com.devsteve.product_mservice.application.ports.in.categoria.*;
import com.devsteve.product_mservice.domain.model.CategoriaModel;
import com.devsteve.product_mservice.domain.ports.out.CategoriaRepository;
import com.devsteve.product_mservice.shared.exceptions.DuplicateResourceException;

import java.util.List;

public class CategoriaService implements
        CrearCategoriaUseCase,
        ListarCategoriasUseCase,
        BuscarCategoriaPorIdUseCase,
        ActualizarCategoriaUseCase,
        EliminarCategoriaUseCase,
        BuscarCategoriaPorNombreUseCase,
        ObtenerNombreCategoriaUseCase{
    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public CategoriaModel crearCategoria(CategoriaModel categoriaModel) {
        // Regla de negocio: validar si la categoría ya existe
        if (categoriaRepository.existePorNombre(categoriaModel.getNombre())) {
            throw new DuplicateResourceException("La categoría ya existe");
        }

        // Guardar la categoria en el repositorio
        return categoriaRepository.guardar(categoriaModel);
    }

    @Override
    public List<CategoriaModel> listarCategorias(int page, int size) {
        return categoriaRepository.buscarTodas(page, size);
    }

    @Override
    public CategoriaModel buscarPorId(Long id) {
        return categoriaRepository.buscarPorId(id);
    }

    @Override
    public CategoriaModel actualizar(Long id, CategoriaModel model) {
        CategoriaModel existente = categoriaRepository.buscarPorId(id);

        // Regla de negocio: validar si la categoría ya existe
        if (categoriaRepository.existePorNombre(model.getNombre()) && !existente.getNombre().equalsIgnoreCase(model.getNombre())) {
            throw new DuplicateResourceException("El nombre de la categoría ya existe");
        }

        existente.setNombre(model.getNombre());
        return categoriaRepository.guardar(existente);
    }

    @Override
    public void eliminar(Long id) {
        this.buscarPorId(id);
        categoriaRepository.eliminar(id);
    }

    @Override
    public List<CategoriaModel> buscarPorNombre(String nombre, int page, int size) {
        return categoriaRepository.buscarPorNombre(nombre, page, size);
    }

    @Override
    public String obtenerNombrePorId(Long id) {
        return categoriaRepository.buscarPorId(id).getNombre();
    }
}
