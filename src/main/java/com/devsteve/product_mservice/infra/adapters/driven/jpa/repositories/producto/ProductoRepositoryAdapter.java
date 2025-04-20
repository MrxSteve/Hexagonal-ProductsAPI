package com.devsteve.product_mservice.infra.adapters.driven.jpa.repositories.producto;

import com.devsteve.product_mservice.domain.model.ProductoModel;
import com.devsteve.product_mservice.domain.model.enums.EstadoProducto;
import com.devsteve.product_mservice.domain.ports.out.ProductoRepository;
import com.devsteve.product_mservice.infra.adapters.driven.jpa.entities.ProductoEntity;
import com.devsteve.product_mservice.infra.adapters.driven.jpa.mappers.producto.ProductoEntityMapper;
import com.devsteve.product_mservice.infra.adapters.driven.jpa.specifications.ProductoSpecification;
import com.devsteve.product_mservice.shared.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductoRepositoryAdapter implements ProductoRepository {
    private final JpaProductoRepository jpaProductoRepository;
    private final ProductoEntityMapper productoEntityMapper;

    @Override
    public ProductoModel guardar(ProductoModel productoModel) {
        ProductoEntity entity = productoEntityMapper.toEntity(productoModel);
        ProductoEntity savedEntity = jpaProductoRepository.save(entity);

        return productoEntityMapper.toDomain(savedEntity);
    }

    @Override
    public ProductoModel buscarPorId(Long id) {
        ProductoEntity entity = jpaProductoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto con id " + id + " no encontrado"));

        return productoEntityMapper.toDomain(entity);
    }

    @Override
    public List<ProductoModel> buscarTodas(int page, int size) {
        return jpaProductoRepository.findAll(PageRequest.of(page, size))
                .getContent()
                .stream()
                .map(productoEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void eliminar(Long id) {
        this.buscarPorId(id);
        jpaProductoRepository.deleteById(id);
    }

    @Override
    public boolean existePorNombre(String nombre) {
        return jpaProductoRepository.existsByNombreIgnoreCase(nombre);
    }

    @Override
    public List<ProductoModel> buscarConFiltros(
            Optional<String> nombre,
            Optional<Long> marcaId,
            Optional<Long> categoriaId,
            Optional<EstadoProducto> estado,
            int page,
            int size
    ) {
        Specification<ProductoEntity> spec = Specification.where(null);

        if (nombre.isPresent()) {
            spec = spec.and(ProductoSpecification.nombreContiene(nombre.get()));
        }

        if (marcaId.isPresent()) {
            spec = spec.and(ProductoSpecification.marcaIdEs(marcaId.get()));
        }

        if (categoriaId.isPresent()) {
            spec = spec.and(ProductoSpecification.categoriaIdEs(categoriaId.get()));
        }

        if (estado.isPresent()) {
            spec = spec.and(ProductoSpecification.estadoEs(estado.get()));
        }

        return jpaProductoRepository.findAll(spec, PageRequest.of(page, size))
                .getContent()
                .stream()
                .map(productoEntityMapper::toDomain)
                .collect(Collectors.toList());
    }
}
