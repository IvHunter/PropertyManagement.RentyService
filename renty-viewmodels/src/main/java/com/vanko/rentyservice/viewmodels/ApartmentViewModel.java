package com.vanko.rentyservice.viewmodels;

import com.vanko.rentyservice.commonmodels.ApartmentType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ApartmentViewModel {
    private Long id;

    @Size(min = 1, max =  100, message = "Name must be between 1 and 100 characters")
    private String name;

    @NotNull(message = "Type cannot be null")
    private ApartmentType type;

    public ApartmentViewModel() {
    }

    public ApartmentViewModel(String name, ApartmentType type) {
        this.name = name;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ApartmentType getType() {
        return this.type;
    }

    public void setType(ApartmentType apartmentType) {
        this.type = apartmentType;
    }
}
