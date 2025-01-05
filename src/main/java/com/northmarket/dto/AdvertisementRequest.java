package com.northmarket.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class AdvertisementRequest {
    private String title;
    private String description;
    private BigDecimal budget;
    private String startDate; // Will parse to LocalDateTime
    private String endDate;
}
