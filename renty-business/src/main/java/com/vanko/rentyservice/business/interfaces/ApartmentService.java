package com.vanko.rentyservice.business.interfaces;

import com.vanko.rentyservice.viewmodels.ApartmentViewModel;
import com.vanko.rentyservice.viewmodels.SellApartmentRequestModel;

public interface ApartmentService {
    long addApartment(ApartmentViewModel apartmentView, long landlordId);

    ApartmentViewModel getApartmentView(long id);

    long sellApartment(SellApartmentRequestModel request);
}
