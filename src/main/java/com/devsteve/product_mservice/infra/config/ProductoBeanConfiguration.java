package com.devsteve.product_mservice.infra.config;

import com.devsteve.product_mservice.application.ports.in.categoria.BuscarCategoriaPorIdUseCase;
import com.devsteve.product_mservice.application.ports.in.marca.BuscarMarcaPorIdUseCase;
import com.devsteve.product_mservice.application.ports.in.producto.crud.*;
import com.devsteve.product_mservice.application.ports.in.producto.filters.*;
import com.devsteve.product_mservice.application.services.ProductoService;
import com.devsteve.product_mservice.domain.ports.out.ProductoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductoBeanConfiguration {
    @Bean
    public ProductoService productoService(ProductoRepository productoRepository,
                                           BuscarMarcaPorIdUseCase buscarMarcaPorIdUseCase,
                                           BuscarCategoriaPorIdUseCase buscarCategoriaPorIdUseCase) {
        return new ProductoService(productoRepository, buscarMarcaPorIdUseCase, buscarCategoriaPorIdUseCase);
    }

    @Bean
    public CrearProductoUseCase crearProductoUseCase(ProductoService service) {
        return service;
    }

    @Bean
    public ListarProductosUseCase listarProductosUseCase(ProductoService service) {
        return service;
    }

    @Bean
    public BuscarProductoPorIdUseCase buscarProductoPorIdUseCase(ProductoService service) {
        return service;
    }

    @Bean
    public ActualizarProductoUseCase actualizarProductoUseCase(ProductoService service) {
        return service;
    }

    @Bean
    public EliminarProductoUseCase eliminarProductoUseCase(ProductoService service) {
        return service;
    }

    @Bean
    public BuscarProductosPorFiltrosUseCase buscarProductosPorFiltrosUseCase(ProductoService service) {
        return service;
    }
}
