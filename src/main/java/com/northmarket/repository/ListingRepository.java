package com.northmarket.repository;

import com.northmarket.model.Listing;
import com.northmarket.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListingRepository extends JpaRepository<Listing, Long> {
    List<Listing> findBySeller(User seller);
    List<Listing> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String t, String d);
}
