package com.northmarket.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class AdvertisementResponse {
    private Long id;
    private String title;
    private String description;
    private BigDecimal budget;
    private String status;
    private Integer impressions;
    private Integer clicks;
    private Double ctr;
    private String sellerName;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime createdAt;
}
