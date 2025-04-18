package com.devsteve.product_mservice.infra.adapters.driven.jpa.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "marcas")
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @Builder
public class MarcaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String nombre;
}
