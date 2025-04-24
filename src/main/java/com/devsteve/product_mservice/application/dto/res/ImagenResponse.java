package com.devsteve.product_mservice.application.dto.res;

public class ImagenResponse {
    private Long id;
    private String url;

    public ImagenResponse() {
    }

    public ImagenResponse(Long id, String url) {
        this.id = id;
        this.url = url;
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
}
