package com.vanko.rentyservice.business.interfaces.mappers;

import com.vanko.rentyservice.data.Apartment;
import com.vanko.rentyservice.data.Landlord;
import com.vanko.rentyservice.viewmodels.ApartmentViewModel;

public interface IApartmentMapper {
    ApartmentViewModel mapApartmentToView(Apartment apartment);

    Apartment mapApartmentFromView(ApartmentViewModel apartmentView, Landlord landlord);
}
