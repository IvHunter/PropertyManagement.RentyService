package com.vanko.rentyservice.viewmodels;

import jakarta.validation.constraints.Min;

import java.math.BigDecimal;

public class SellApartmentRequestModel {
    @Min(value = 1, message = "Apartment ID must be positive!")
    private long apartmentId;

    @Min(value = 1, message = "New landlord ID must be positive!")
    private long newLandlordId;

    @Min(value = 1, message = "Apartment selling price must be positive!")
    private BigDecimal price;

    public SellApartmentRequestModel() {
    }

    public long getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(long apartmentId) {
        this.apartmentId = apartmentId;
    }

    public long getNewLandlordId() {
        return newLandlordId;
    }

    public void setNewLandlordId(long newLandlordId) {
        this.newLandlordId = newLandlordId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
