package com.vanko.rentyservice.business.interfaces.mappers;

import com.vanko.rentyservice.data.Apartment;
import com.vanko.rentyservice.data.Landlord;
import com.vanko.rentyservice.viewmodels.ApartmentDto;

public interface ApartmentMapper {
    ApartmentDto mapApartmentToView(Apartment apartment);

    Apartment mapApartmentFromView(ApartmentDto apartmentView, Landlord landlord);
}
