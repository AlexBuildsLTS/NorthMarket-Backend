package com.northmarket.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="advertisements")
public class Advertisement {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition="TEXT")
    private String description;

    private BigDecimal budget;
    private String status="active";
    private int impressions=0;
    private int clicks=0;
    private double ctr=0.0;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="seller_id", nullable=false)
    private User seller;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private LocalDateTime createdAt=LocalDateTime.now();
    private LocalDateTime updatedAt=LocalDateTime.now();
}
