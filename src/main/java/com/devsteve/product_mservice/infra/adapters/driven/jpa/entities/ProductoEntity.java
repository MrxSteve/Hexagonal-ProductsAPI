package com.devsteve.product_mservice.infra.adapters.driven.jpa.entities;

import com.devsteve.product_mservice.domain.model.enums.EstadoProducto;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "productos")
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @Builder
public class ProductoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = true, length = 255)
    private String descripcion;

    @Column(nullable = false)
    private BigDecimal precio;

    @Column(name = "marca_id")
    private Long marcaId;

    @Column(name = "categoria_id")
    private Long categoriaId;

    @Enumerated(EnumType.STRING)
    private EstadoProducto estado;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @OneToMany(
            mappedBy = "productoEntity",
            cascade = CascadeType.ALL,
           fetch = FetchType.LAZY
    )
    private List<ImagenEntity> imagenes;
}
