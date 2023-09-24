package com.vanko.rentyservice.viewmodels;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import java.util.List;

public class LandlordViewModel {
    private long id;

    @Size(min = 1, max =  100, message = "First Name must be between 1 and 100 characters")
    private String firstName;

    @Size(min = 1, max =  100, message = "Last Name must be between 1 and 100 characters")
    private String lastName;

    @Size(min = 1, max =  100, message = "Email must be between 1 and 100 characters")
    private String email;

    private List<ApartmentViewModel> apartments;

    public LandlordViewModel() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<ApartmentViewModel> getApartments() {
        return apartments;
    }

    public void setApartments(List<ApartmentViewModel> apartments) {
        this.apartments = apartments;
    }
}
