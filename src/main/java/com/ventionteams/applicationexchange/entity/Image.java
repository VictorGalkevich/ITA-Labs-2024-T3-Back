package com.ventionteams.applicationexchange.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name="lot_id", nullable=false)
    private Lot lot;

    @Transient
    private byte[] bytes;
}
