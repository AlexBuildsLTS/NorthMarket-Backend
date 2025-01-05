package com.northmarket.service;

import com.northmarket.model.Advertisement;
import com.northmarket.model.Listing;
import com.northmarket.repository.AdvertisementRepository;
import com.northmarket.repository.ListingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final ListingRepository listingRepository;
    private final AdvertisementRepository advertisementRepository;

    public List<Listing> searchListings(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return listingRepository.findAll();
        }
        return listingRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(keyword, keyword);
    }

    public List<Advertisement> searchAdvertisements(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return advertisementRepository.findAll();
        }
        return advertisementRepository.findByTitleContainingIgnoreCase(keyword);
    }
}
