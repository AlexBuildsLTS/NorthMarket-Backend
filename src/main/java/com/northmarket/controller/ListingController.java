package com.northmarket.controller;

import com.northmarket.model.Listing;
import com.northmarket.service.ListingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/listings")
@RequiredArgsConstructor
public class ListingController {

    private final ListingService listingService;

    @PostMapping
    @PreAuthorize("hasRole('SELLER')")
    public ResponseEntity<Listing> createListing(@RequestBody Listing l) {
        return ResponseEntity.ok(listingService.createListing(l));
    }

    @GetMapping("/seller")
    @PreAuthorize("hasRole('SELLER')")
    public ResponseEntity<List<Listing>> getSellerListings() {
        return ResponseEntity.ok(listingService.getSellerListings());
    }

    @GetMapping
    public ResponseEntity<List<Listing>> getAllListings(
            @RequestParam(required=false) String category,
            @RequestParam(defaultValue="0") int page,
            @RequestParam(defaultValue="10") int size
    ) {
        return ResponseEntity.ok(listingService.getAllListings(category, page, size));
    }
}
