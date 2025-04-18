package com.devsteve.product_mservice.infra.config;

import com.devsteve.product_mservice.application.ports.in.marca.*;
import com.devsteve.product_mservice.application.services.MarcaService;
import com.devsteve.product_mservice.domain.ports.out.MarcaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MarcaBeanConfiguration {
    @Bean
    public MarcaService marcaService(MarcaRepository marcaRepository) {
        return new MarcaService(marcaRepository);
    }

    @Bean
    public CrearMarcaUseCase crearMarcaUseCase(MarcaService service) {
        return service;
    }

    @Bean
    public ListarMarcasUseCase listarMarcasUseCase(MarcaService service) {
        return service;
    }

    @Bean
    public BuscarMarcaPorIdUseCase buscarMarcaPorIdUseCase(MarcaService service) {
        return service;
    }

    @Bean
    public ActualizarMarcaUseCase actualizarMarcaUseCase(MarcaService service) {
        return service;
    }

    @Bean
    public EliminarMarcaUseCase eliminarMarcaUseCase(MarcaService service) {
        return service;
    }

    @Bean
    public BuscarMarcaPorNombreUseCase buscarMarcaPorNombreUseCase(MarcaService service) {
        return service;
    }
}
