package com.devsteve.product_mservice.domain.ports.out;

import com.devsteve.product_mservice.domain.model.CategoriaModel;

import java.util.List;

public interface CategoriaRepository {
    CategoriaModel guardar(CategoriaModel categoria);
    CategoriaModel buscarPorId(Long id);
    List<CategoriaModel> buscarTodas(int page, int size);
    void eliminar(Long id);
    boolean existePorNombre(String nombre);
    List<CategoriaModel> buscarPorNombre(String nombre, int page, int size);
}
