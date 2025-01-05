package com.northmarket.service;

import com.northmarket.dto.AdvertisementRequest;
import com.northmarket.dto.AdvertisementResponse;

import java.util.List;

public interface AdvertisementService {
    AdvertisementResponse createAdvertisement(AdvertisementRequest req);
    AdvertisementResponse updateAdvertisement(Long id, AdvertisementRequest req);
    void deleteAdvertisement(Long id);
    AdvertisementResponse getAdvertisement(Long id);
    List<AdvertisementResponse> getSellerAdvertisements();
    void recordImpression(Long id);
    void recordClick(Long id);
    void updateStatus(Long id, String status);
}
