package com.northmarket.repository;

import com.northmarket.model.Advertisement;
import com.northmarket.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {
    List<Advertisement> findBySeller(User seller);
    List<Advertisement> findByTitleContainingIgnoreCase(String keyword);
}
