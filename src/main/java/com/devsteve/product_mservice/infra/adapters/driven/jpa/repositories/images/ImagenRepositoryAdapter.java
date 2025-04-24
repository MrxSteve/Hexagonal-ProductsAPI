package com.devsteve.product_mservice.infra.adapters.driven.jpa.repositories.images;

import com.devsteve.product_mservice.domain.model.ImagenModel;
import com.devsteve.product_mservice.domain.ports.out.ImagenRepository;
import com.devsteve.product_mservice.infra.adapters.driven.jpa.entities.ImagenEntity;
import com.devsteve.product_mservice.infra.adapters.driven.jpa.entities.ProductoEntity;
import com.devsteve.product_mservice.infra.adapters.driven.jpa.mappers.images.ImagenEntityMapper;
import com.devsteve.product_mservice.infra.adapters.driven.jpa.repositories.producto.JpaProductoRepository;
import com.devsteve.product_mservice.shared.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ImagenRepositoryAdapter implements ImagenRepository {
    private final JpaImagenRepository jpaImagenRepository;
    private final ImagenEntityMapper imagenEntityMapper;

    private final JpaProductoRepository jpaProductoRepository;

    @Override
    public ImagenModel guardar(ImagenModel imagenModel) {
        ImagenEntity entity = imagenEntityMapper.toEntity(imagenModel);

        // Buscar Producto
        ProductoEntity productoEntity = jpaProductoRepository.findById(imagenModel.getProductoId())
                .orElseThrow(() -> new ResourceNotFoundException("Producto con id " + imagenModel.getProductoId() + " no encontrado"));

        entity.setProductoEntity(productoEntity);

        ImagenEntity savedEntity = jpaImagenRepository.save(entity);
        return imagenEntityMapper.toDomain(savedEntity);
    }

    @Override
    public ImagenModel buscarPorId(Long id) {
        ImagenEntity entity = jpaImagenRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Imagen con id " + id + " no encontrada"));
        return imagenEntityMapper.toDomain(entity);
    }

    @Override
    public List<ImagenModel> buscarPorProductoId(Long productoId) {
        return jpaImagenRepository.findByProductoEntity_Id(productoId)
                .stream()
                .map(imagenEntityMapper::toDomain)
                .toList();
    }

    @Override
    public void eliminar(ImagenModel imagen) {
        ImagenEntity entity = imagenEntityMapper.toEntity(imagen);
        jpaImagenRepository.delete(entity);
    }
}
