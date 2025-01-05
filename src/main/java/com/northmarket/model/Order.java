package com.northmarket.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="buyer_id")
    private User buyer;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="order_id")
    private List<OrderItem> items=new ArrayList<>();

    private BigDecimal total;
    private String status;
    private LocalDateTime createdAt=LocalDateTime.now();
}
