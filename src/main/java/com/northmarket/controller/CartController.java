package com.northmarket.controller;

import com.northmarket.model.CartItem;
import com.northmarket.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<CartItem> addItem(@RequestParam Long listingId,
                                            @RequestParam(defaultValue="1") int quantity) {
        return ResponseEntity.ok(cartService.addItemToCart(listingId, quantity));
    }

    @GetMapping
    public ResponseEntity<List<CartItem>> getCartItems() {
        return ResponseEntity.ok(cartService.getCartItems());
    }
}
