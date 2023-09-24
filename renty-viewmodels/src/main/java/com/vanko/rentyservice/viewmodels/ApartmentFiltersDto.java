package com.vanko.rentyservice.viewmodels;

import com.vanko.rentyservice.commonmodels.ApartmentType;

public class ApartmentFiltersDto {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ApartmentType getType() {
        return type;
    }

    public void setType(ApartmentType type) {
        this.type = type;
    }

    public long getLandlordId() {
        return landlordId;
    }

    public void setLandlordId(long landlordId) {
        this.landlordId = landlordId;
    }

    private ApartmentType type;

    private long landlordId;

    public ApartmentFiltersDto() {

    }
}
