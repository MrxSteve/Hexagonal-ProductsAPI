package com.devsteve.product_mservice.infra.adapters.driven.jpa.repositories.categoria;

import com.devsteve.product_mservice.infra.adapters.driven.jpa.entities.CategoriaEntity;
import com.devsteve.product_mservice.infra.adapters.driven.jpa.mappers.categoria.CategoriaEntityMapper;
import com.devsteve.product_mservice.domain.model.CategoriaModel;
import com.devsteve.product_mservice.domain.ports.out.CategoriaRepository;
import com.devsteve.product_mservice.shared.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CategoriaRepositoryAdapter implements CategoriaRepository {
    private final JpaCategoriaRepository jpaCategoriaRepository;
    private final CategoriaEntityMapper categoriaEntityMapper;

    @Override
    public CategoriaModel guardar(CategoriaModel categoria) {
        CategoriaEntity entity = categoriaEntityMapper.toEntity(categoria);
        CategoriaEntity savedEntity = jpaCategoriaRepository.save(entity);
        return categoriaEntityMapper.toDomain(savedEntity);
    }

    @Override
    public CategoriaModel buscarPorId(Long id) {
        CategoriaEntity entity = jpaCategoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria con id " + id + " no encontrada"));

        return categoriaEntityMapper.toDomain(entity);
    }

    @Override
    public List<CategoriaModel> buscarTodas(int page, int size) {
        return jpaCategoriaRepository.findAll(PageRequest.of(page, size))
                .getContent()
                .stream()
                .map(categoriaEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void eliminar(Long id) {
        buscarPorId(id);
        jpaCategoriaRepository.deleteById(id);
    }

    @Override
    public boolean existePorNombre(String nombre) {
        return jpaCategoriaRepository.existsByNombreIgnoreCase(nombre);
    }

    @Override
    public List<CategoriaModel> buscarPorNombre(String nombre, int page, int size) {
        return jpaCategoriaRepository.
                findByNombreContainingIgnoreCase(nombre, PageRequest.of(page, size))
                .getContent()
                .stream()
                .map(categoriaEntityMapper::toDomain)
                .collect(Collectors.toList());
    }
}
