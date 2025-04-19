package com.devsteve.product_mservice.application.services;

import com.devsteve.product_mservice.application.ports.in.marca.*;
import com.devsteve.product_mservice.domain.model.MarcaModel;
import com.devsteve.product_mservice.domain.ports.out.MarcaRepository;
import com.devsteve.product_mservice.shared.exceptions.DuplicateResourceException;

import java.util.List;

public class MarcaService implements
        CrearMarcaUseCase,
        ListarMarcasUseCase,
        BuscarMarcaPorIdUseCase,
        ActualizarMarcaUseCase,
        EliminarMarcaUseCase,
        BuscarMarcaPorNombreUseCase,
        ObtenerNombreMarcaUseCase{
    private final MarcaRepository marcaRepository;

    public MarcaService(MarcaRepository marcaRepository) {
        this.marcaRepository = marcaRepository;
    }

    @Override
    public MarcaModel crearMarca(MarcaModel marcaModel) {
        // Validar la marca
        if (marcaRepository.existePorNombre(marcaModel.getNombre())) {
            throw new DuplicateResourceException("La marca ya existe");
        }

        // Guardar la marca en el repositorio
        return marcaRepository.guardar(marcaModel);
    }

    @Override
    public List<MarcaModel> listarMarcas(int page, int size) {
        return marcaRepository.buscarTodas(page, size);
    }

    @Override
    public MarcaModel buscarPorId(Long id) {
        return marcaRepository.buscarPorId(id);
    }

    @Override
    public MarcaModel actualizar(Long id, MarcaModel model) {
        MarcaModel existente = marcaRepository.buscarPorId(id);

        // Validar si la marca ya existe
        if (marcaRepository.existePorNombre(model.getNombre()) && !existente.getNombre().equalsIgnoreCase(model.getNombre())) {
            throw new DuplicateResourceException("El nombre de la marca ya existe");
        }

        existente.setNombre(model.getNombre());
        return marcaRepository.guardar(existente);
    }

    @Override
    public void eliminar(Long id) {
        this.buscarPorId(id);
        marcaRepository.eliminar(id);
    }

    @Override
    public List<MarcaModel> buscarPorNombre(String nombre, int page, int size) {
        return marcaRepository.buscarPorNombre(nombre, page, size);
    }

    @Override
    public String obtenerNombrePorId(Long id) {
        return marcaRepository.buscarPorId(id).getNombre();
    }
}
