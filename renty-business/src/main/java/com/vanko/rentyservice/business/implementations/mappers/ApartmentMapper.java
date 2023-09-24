package com.vanko.rentyservice.business.implementations.mappers;

import com.vanko.rentyservice.data.Apartment;
import com.vanko.rentyservice.data.Landlord;
import com.vanko.rentyservice.viewmodels.ApartmentViewModel;
import org.springframework.stereotype.Service;
import com.vanko.rentyservice.business.interfaces.mappers.IApartmentMapper;

@Service
public class ApartmentMapper implements IApartmentMapper {
    public ApartmentViewModel mapApartmentToView(Apartment apartment) {
        ApartmentViewModel model = new ApartmentViewModel();
        model.setId(apartment.getId());
        model.setName(apartment.getName());
        model.setType(apartment.getType());

        return model;
    }

    public Apartment mapApartmentFromView(ApartmentViewModel apartmentView, Landlord landlord) {
        Apartment apartment = new Apartment();
        apartment.setName(apartmentView.getName());
        apartment.setType(apartmentView.getType());
        apartment.setLandlord(landlord);

        return apartment;
    }
}
