package com.northmarket.controller;

import com.northmarket.model.Advertisement;
import com.northmarket.model.Listing;
import com.northmarket.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/search")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @GetMapping("/listings")
    public List<Listing> searchListings(@RequestParam(required=false) String keyword) {
        return searchService.searchListings(keyword);
    }

    @GetMapping("/advertisements")
    public List<Advertisement> searchAdvertisements(@RequestParam(required=false) String keyword) {
        return searchService.searchAdvertisements(keyword);
    }
}
