package com.vanko.rentyservice.business.interfaces;

import com.vanko.rentyservice.viewmodels.ApartmentViewModel;

public interface IApartmentService {
    long addApartment(ApartmentViewModel apartmentView, long landlordId);

    ApartmentViewModel getApartmentView(long id);
}
