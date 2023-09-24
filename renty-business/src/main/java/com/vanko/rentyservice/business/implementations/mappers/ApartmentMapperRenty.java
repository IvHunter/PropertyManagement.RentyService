package com.vanko.rentyservice.business.implementations.mappers;

import com.vanko.rentyservice.data.Apartment;
import com.vanko.rentyservice.data.Landlord;
import com.vanko.rentyservice.viewmodels.ApartmentDto;
import org.springframework.stereotype.Service;
import com.vanko.rentyservice.business.interfaces.mappers.ApartmentMapper;

@Service
public class ApartmentMapperRenty implements ApartmentMapper {
    public ApartmentDto mapApartmentToView(Apartment apartment) {
        ApartmentDto model = new ApartmentDto();
        model.setId(apartment.getId());
        model.setName(apartment.getName());
        model.setType(apartment.getType());

        return model;
    }

    public Apartment mapApartmentFromView(ApartmentDto apartmentView, Landlord landlord) {
        Apartment apartment = new Apartment();
        apartment.setName(apartmentView.getName());
        apartment.setType(apartmentView.getType());
        apartment.setLandlord(landlord);

        return apartment;
    }
}
