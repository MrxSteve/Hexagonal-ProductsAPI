package com.devsteve.product_mservice.infra.adapters.driven.jpa.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "imagenes")
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @Builder
public class ImagenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private ProductoEntity productoEntity;
}
