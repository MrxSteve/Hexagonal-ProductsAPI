package com.devsteve.product_mservice.domain.model;

import com.devsteve.product_mservice.domain.model.enums.EstadoProducto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class ProductoModel {
    private Long id;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;

    private Long marcaId;
    private Long categoriaId;

    private EstadoProducto estado;
    private LocalDateTime fechaCreacion;

    public ProductoModel() {
    }

    public ProductoModel(Long id, String nombre, String descripcion, BigDecimal precio,
                         Long marcaId, Long categoriaId, EstadoProducto estado,
                         LocalDateTime fechaCreacion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.marcaId = marcaId;
        this.categoriaId = categoriaId;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
    }

    // Getters y setters

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Long getMarcaId() {
        return marcaId;
    }

    public void setMarcaId(Long marcaId) {
        this.marcaId = marcaId;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public EstadoProducto getEstado() {
        return estado;
    }

    public void setEstado(EstadoProducto estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductoModel)) return false;
        ProductoModel that = (ProductoModel) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
