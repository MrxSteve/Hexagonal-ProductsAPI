package com.devsteve.product_mservice.infra.config;

import com.devsteve.product_mservice.application.ports.in.categoria.*;
import com.devsteve.product_mservice.application.services.CategoriaService;
import com.devsteve.product_mservice.domain.ports.out.CategoriaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CategoriaBeanConfiguration {
    @Bean
    public CategoriaService categoriaService(CategoriaRepository categoriaRepository) {
        return new CategoriaService(categoriaRepository);
    }

    @Bean
    public CrearCategoriaUseCase crearCategoriaUseCase(CategoriaService service) {
        return service;
    }

    @Bean
    public ListarCategoriasUseCase listarCategoriasUseCase(CategoriaService service) {
        return service;
    }

    @Bean
    public BuscarCategoriaPorIdUseCase buscarCategoriaPorIdUseCase(CategoriaService service) {
        return service;
    }

    @Bean
    public ActualizarCategoriaUseCase actualizarCategoriaUseCase(CategoriaService service) {
        return service;
    }

    @Bean
    public EliminarCategoriaUseCase eliminarCategoriaUseCase(CategoriaService service) {
        return service;
    }

    @Bean
    public BuscarCategoriaPorNombreUseCase buscarCategoriaPorNombreUseCase(CategoriaService service) {
        return service;
    }

    @Bean
    public ObtenerNombreCategoriaUseCase obtenerNombreCategoriaUseCase(CategoriaService service) {
        return service;
    }
}
