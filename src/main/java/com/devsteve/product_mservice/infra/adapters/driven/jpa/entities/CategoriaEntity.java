package com.devsteve.product_mservice.infra.adapters.driven.jpa.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "categorias")
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @Builder
public class CategoriaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String nombre;


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
}
