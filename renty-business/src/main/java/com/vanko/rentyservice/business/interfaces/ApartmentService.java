package com.vanko.rentyservice.business.interfaces;

import com.vanko.rentyservice.viewmodels.ApartmentFiltersDto;
import com.vanko.rentyservice.viewmodels.ApartmentDto;
import com.vanko.rentyservice.viewmodels.SellApartmentDto;

import java.util.List;

public interface ApartmentService {
    long addApartment(ApartmentDto apartmentView, long landlordId);

    ApartmentDto getApartmentView(long id);

    List<ApartmentDto> getApartmentsByFilters(ApartmentFiltersDto filters);

    long sellApartment(SellApartmentDto request);
}
