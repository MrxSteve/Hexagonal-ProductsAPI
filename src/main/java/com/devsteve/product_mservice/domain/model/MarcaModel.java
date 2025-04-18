package com.devsteve.product_mservice.domain.model;

import java.util.Objects;

public class MarcaModel {
    private Long id;
    private String nombre;

    public MarcaModel() {
    }

    public MarcaModel(Long id, String nombre) {
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
        if (!(o instanceof MarcaModel)) return false;
        MarcaModel marcaModel = (MarcaModel) o;
        return Objects.equals(id, marcaModel.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
