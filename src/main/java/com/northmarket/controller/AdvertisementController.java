package com.northmarket.controller;

import com.northmarket.dto.AdvertisementRequest;
import com.northmarket.dto.AdvertisementResponse;
import com.northmarket.service.AdvertisementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/advertisements")
@RequiredArgsConstructor
public class AdvertisementController {

    private final AdvertisementService advertisementService;

    @PostMapping
    @PreAuthorize("hasRole('SELLER')")
    public ResponseEntity<AdvertisementResponse> createAd(@RequestBody AdvertisementRequest r) {
        return ResponseEntity.ok(advertisementService.createAdvertisement(r));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('SELLER')")
    public ResponseEntity<AdvertisementResponse> updateAd(@PathVariable Long id, @RequestBody AdvertisementRequest r) {
        return ResponseEntity.ok(advertisementService.updateAdvertisement(id, r));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('SELLER')")
    public ResponseEntity<Void> deleteAd(@PathVariable Long id) {
        advertisementService.deleteAdvertisement(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdvertisementResponse> getAd(@PathVariable Long id) {
        return ResponseEntity.ok(advertisementService.getAdvertisement(id));
    }

    @GetMapping("/seller")
    @PreAuthorize("hasRole('SELLER')")
    public ResponseEntity<List<AdvertisementResponse>> getSellerAds() {
        return ResponseEntity.ok(advertisementService.getSellerAdvertisements());
    }

    @PostMapping("/{id}/impression")
    public ResponseEntity<Void> recordImpression(@PathVariable Long id) {
        advertisementService.recordImpression(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/click")
    public ResponseEntity<Void> recordClick(@PathVariable Long id) {
        advertisementService.recordClick(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/status")
    @PreAuthorize("hasRole('SELLER')")
    public ResponseEntity<Void> updateStatus(@PathVariable Long id, @RequestParam String status) {
        advertisementService.updateStatus(id, status);
        return ResponseEntity.ok().build();
    }
}
