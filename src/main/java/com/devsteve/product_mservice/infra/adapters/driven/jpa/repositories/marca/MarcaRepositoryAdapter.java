package com.devsteve.product_mservice.infra.adapters.driven.jpa.repositories.marca;

import com.devsteve.product_mservice.domain.model.MarcaModel;
import com.devsteve.product_mservice.domain.ports.out.MarcaRepository;
import com.devsteve.product_mservice.infra.adapters.driven.jpa.entities.MarcaEntity;
import com.devsteve.product_mservice.infra.adapters.driven.jpa.mappers.marca.MarcaEntityMapper;
import com.devsteve.product_mservice.shared.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MarcaRepositoryAdapter implements MarcaRepository {
    private final JpaMarcaRepository jpaMarcaRepository;
    private final MarcaEntityMapper marcaEntityMapper;

    @Override
    public MarcaModel guardar(MarcaModel model) {
        MarcaEntity entity = marcaEntityMapper.toEntity(model);
        MarcaEntity savedEntity = jpaMarcaRepository.save(entity);
        return marcaEntityMapper.toDomain(savedEntity);
    }

    @Override
    public MarcaModel buscarPorId(Long id) {
        MarcaEntity entity = jpaMarcaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Marca con id " + id + " no encontrada"));

        return marcaEntityMapper.toDomain(entity);
    }

    @Override
    public List<MarcaModel> buscarTodas(int page, int size) {
        return jpaMarcaRepository.findAll(PageRequest.of(page, size))
                .getContent()
                .stream()
                .map(marcaEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void eliminar(Long id) {
        buscarPorId(id);
        jpaMarcaRepository.deleteById(id);
    }

    @Override
    public boolean existePorNombre(String nombre) {
        return jpaMarcaRepository.existsByNombreIgnoreCase(nombre);
    }

    @Override
    public List<MarcaModel> buscarPorNombre(String nombre, int page, int size) {
        return jpaMarcaRepository.findByNombreContainingIgnoreCase(nombre, PageRequest.of(page, size))
                .getContent()
                .stream()
                .map(marcaEntityMapper::toDomain)
                .collect(Collectors.toList());
    }
}
