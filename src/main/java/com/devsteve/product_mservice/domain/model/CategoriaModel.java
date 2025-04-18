package com.devsteve.product_mservice.domain.model;

import java.util.Objects;

public class CategoriaModel {
    private Long id;
    private String nombre;

    public CategoriaModel() {
    }

    public CategoriaModel(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CategoriaModel)) return false;
        CategoriaModel categoriaModel = (CategoriaModel) o;
        return Objects.equals(id, categoriaModel.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
