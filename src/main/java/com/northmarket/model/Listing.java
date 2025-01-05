package com.northmarket.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="listings")
public class Listing {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="seller_id", nullable=false)
    private User seller;

    private String title;

    @Column(columnDefinition="TEXT")
    private String description;

    private BigDecimal price;

    private String category;

    private double rating=0.0;
    private int ratingCount=0;

    private boolean active=true;

    private LocalDateTime createdAt=LocalDateTime.now();
    private LocalDateTime updatedAt=LocalDateTime.now();
}
