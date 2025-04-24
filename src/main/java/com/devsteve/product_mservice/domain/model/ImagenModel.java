package com.devsteve.product_mservice.domain.model;

import java.util.Objects;

public class ImagenModel {
    private Long id;
    private String url;
    private Long productoId;

    public ImagenModel() {
    }

    public ImagenModel(Long id, String url, Long productoId) {
        this.id = id;
        this.url = url;
        this.productoId = productoId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ImagenModel)) return false;
        ImagenModel that = (ImagenModel) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
