package com.northmarket.service;

import com.northmarket.model.CartItem;
import com.northmarket.model.Listing;
import com.northmarket.model.User;
import com.northmarket.repository.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartItemRepository cartItemRepository;
    private final UserService userService;

    @Transactional
    public CartItem addItemToCart(Long listingId, int qty) {
        User user = userService.getCurrentUser();

        // In real usage, fetch Listing via ListingRepository:
        Listing temp = new Listing();
        temp.setId(listingId);
        // or find it properly from DB

        CartItem ci = new CartItem();
        ci.setUser(user);
        ci.setListing(temp);
        ci.setQuantity(qty);
        return cartItemRepository.save(ci);
    }

    public List<CartItem> getCartItems() {
        return cartItemRepository.findByUser(userService.getCurrentUser());
    }
}
