package com.devsteve.product_mservice.infra.config;

import com.devsteve.product_mservice.application.ports.in.images.*;
import com.devsteve.product_mservice.application.ports.in.producto.crud.BuscarProductoPorIdUseCase;
import com.devsteve.product_mservice.application.services.ImagenService;
import com.devsteve.product_mservice.domain.ports.out.ImagenRepository;
import com.devsteve.product_mservice.domain.ports.out.ImagenStoragePort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ImagenBeanConfiguration {
    @Bean
    public ImagenService imagenService(ImagenRepository repo, ImagenStoragePort storage, BuscarProductoPorIdUseCase buscarProductoPorIdUseCase) {
        return new ImagenService(repo, storage, buscarProductoPorIdUseCase);
    }

    @Bean
    public SubirImagenUseCase subirImagenUseCase(ImagenService service) {
        return service;
    }

    @Bean
    public EliminarImagenPorIdUseCase eliminarImagenPorIdUseCase(ImagenService service) {
        return service;
    }

    @Bean
    public EliminarImagenDeProductoUseCase eliminarImagenDeProductoUseCase(ImagenService service) {
        return service;
    }

    @Bean
    public EliminarTodasImagenesDeProductoUseCase eliminarTodasImagenesDeProductoUseCase(ImagenService service) {
        return service;
    }

    @Bean
    public ListarImagenesPorProductoUseCase listarImagenesPorProductoUseCase(ImagenService service) {
        return service;
    }

    @Bean
    public BuscarImagenPorIdUseCase buscarImagenPorIdUseCase(ImagenService service) {
        return service;
    }
}
