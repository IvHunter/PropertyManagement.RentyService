package com.vanko.rentyservice.business.interfaces;

import com.vanko.rentyservice.viewmodels.ApartmentViewModel;

public interface ApartmentService {
    long addApartment(ApartmentViewModel apartmentView, long landlordId);

    ApartmentViewModel getApartmentView(long id);
}
