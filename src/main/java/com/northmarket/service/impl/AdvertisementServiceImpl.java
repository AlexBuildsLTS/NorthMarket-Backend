package com.northmarket.service.impl;

import com.northmarket.dto.AdvertisementRequest;
import com.northmarket.dto.AdvertisementResponse;
import com.northmarket.model.Advertisement;
import com.northmarket.model.User;
import com.northmarket.repository.AdvertisementRepository;
import com.northmarket.service.AdvertisementService;
import com.northmarket.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdvertisementServiceImpl implements AdvertisementService {

    private final AdvertisementRepository advertisementRepository;
    private final UserService userService;

    @Transactional
    public AdvertisementResponse createAdvertisement(AdvertisementRequest r) {
        User seller = userService.getCurrentUser();
        Advertisement a = new Advertisement();
        a.setTitle(r.getTitle());
        a.setDescription(r.getDescription());
        a.setBudget(r.getBudget());
        a.setStatus("active");
        a.setSeller(seller);

        if (r.getStartDate() != null) {
            a.setStartDate(LocalDateTime.parse(r.getStartDate()));
        }
        if (r.getEndDate() != null) {
            a.setEndDate(LocalDateTime.parse(r.getEndDate()));
        }

        return mapToResponse(advertisementRepository.save(a));
    }

    @Transactional
    public AdvertisementResponse updateAdvertisement(Long id, AdvertisementRequest r) {
        Advertisement a = getById(id);
        validateOwnership(a);

        a.setTitle(r.getTitle());
        a.setDescription(r.getDescription());
        a.setBudget(r.getBudget());

        if (r.getStartDate() != null) {
            a.setStartDate(LocalDateTime.parse(r.getStartDate()));
        }
        if (r.getEndDate() != null) {
            a.setEndDate(LocalDateTime.parse(r.getEndDate()));
        }

        return mapToResponse(advertisementRepository.save(a));
    }

    @Transactional
    public void deleteAdvertisement(Long id) {
        Advertisement a = getById(id);
        validateOwnership(a);
        advertisementRepository.delete(a);
    }

    public AdvertisementResponse getAdvertisement(Long id) {
        return mapToResponse(getById(id));
    }

    public List<AdvertisementResponse> getSellerAdvertisements() {
        User seller = userService.getCurrentUser();
        return advertisementRepository.findBySeller(seller)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public void recordImpression(Long id) {
        Advertisement a = getById(id);
        a.setImpressions(a.getImpressions() + 1);
        updateCTR(a);
        advertisementRepository.save(a);
    }

    @Transactional
    public void recordClick(Long id) {
        Advertisement a = getById(id);
        a.setClicks(a.getClicks() + 1);
        updateCTR(a);
        advertisementRepository.save(a);
    }

    @Transactional
    public void updateStatus(Long id, String status) {
        Advertisement a = getById(id);
        validateOwnership(a);
        a.setStatus(status);
        advertisementRepository.save(a);
    }

    private Advertisement getById(Long id) {
        return advertisementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Advertisement not found"));
    }

    private void validateOwnership(Advertisement a) {
        User current = userService.getCurrentUser();
        if (!a.getSeller().getId().equals(current.getId())) {
            throw new RuntimeException("Not authorized");
        }
    }

    private void updateCTR(Advertisement a) {
        if (a.getImpressions() > 0) {
            double ctr = (double) a.getClicks() / a.getImpressions() * 100;
            a.setCtr(Math.round(ctr * 100.0) / 100.0);
        }
    }

    private AdvertisementResponse mapToResponse(Advertisement a) {
        AdvertisementResponse resp = new AdvertisementResponse();
        resp.setId(a.getId());
        resp.setTitle(a.getTitle());
        resp.setDescription(a.getDescription());
        resp.setBudget(a.getBudget());
        resp.setStatus(a.getStatus());
        resp.setImpressions(a.getImpressions());
        resp.setClicks(a.getClicks());
        resp.setCtr(a.getCtr());
        resp.setSellerName(a.getSeller().getName());
        resp.setStartDate(a.getStartDate());
        resp.setEndDate(a.getEndDate());
        resp.setCreatedAt(a.getCreatedAt());
        return resp;
    }
}
