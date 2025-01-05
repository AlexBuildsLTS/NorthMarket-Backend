package com.northmarket.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="cart_items")
public class CartItem {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="listing_id")
    private Listing listing;

    private int quantity=1;
}
