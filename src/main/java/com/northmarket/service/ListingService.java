package com.northmarket.service;

import com.northmarket.model.Listing;
import com.northmarket.model.User;
import com.northmarket.repository.ListingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListingService {

    private final ListingRepository listingRepository;
    private final UserService userService;

    @Transactional
    public Listing createListing(Listing l) {
        User seller = userService.getCurrentUser();
        l.setSeller(seller);
        return listingRepository.save(l);
    }

    public List<Listing> getSellerListings() {
        return listingRepository.findBySeller(userService.getCurrentUser());
    }

    public List<Listing> getAllListings(String category, int page, int size) {
        // For simplicity: ignoring pagination, category
        return listingRepository.findAll();
    }
}
