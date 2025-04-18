package com.devsteve.product_mservice.domain.ports.out;

import com.devsteve.product_mservice.domain.model.MarcaModel;

import java.util.List;

public interface MarcaRepository {
    MarcaModel guardar(MarcaModel model);
    MarcaModel buscarPorId(Long id);
    List<MarcaModel> buscarTodas(int page, int size);
    void eliminar(Long id);
    boolean existePorNombre(String nombre);
    List<MarcaModel> buscarPorNombre(String nombre, int page, int size);
}
