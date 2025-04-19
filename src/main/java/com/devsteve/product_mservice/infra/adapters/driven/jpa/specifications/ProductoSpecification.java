package com.devsteve.product_mservice.infra.adapters.driven.jpa.specifications;

import com.devsteve.product_mservice.domain.model.enums.EstadoProducto;
import com.devsteve.product_mservice.infra.adapters.driven.jpa.entities.ProductoEntity;
import org.springframework.data.jpa.domain.Specification;

public class ProductoSpecification {
    public static Specification<ProductoEntity> nombreContiene(String nombre) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("nombre")),
                        "%" + nombre.toLowerCase() + "%"
                );
    }

    public static Specification<ProductoEntity> marcaIdEs(Long marcaId) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("marcaId"), marcaId);
    }

    public static Specification<ProductoEntity> categoriaIdEs(Long categoriaId) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("categoriaId"), categoriaId);
    }

    public static Specification<ProductoEntity> estadoEs(EstadoProducto estado) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("estado"), estado);
    }
}
