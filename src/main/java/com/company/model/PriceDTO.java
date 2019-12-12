package com.company.model;

import java.time.LocalDateTime;

public class PriceDTO {
    private LocalDateTime dateTime;
    private String currentPrice;

    public PriceDTO(LocalDateTime dateTime, String currentPrice) {
        this.dateTime = dateTime;
        this.currentPrice = currentPrice;
    }

    public String getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(String currentPrice) {
        this.currentPrice = currentPrice;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
