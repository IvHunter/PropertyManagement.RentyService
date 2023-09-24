package com.vanko.rentyservice.business.implementations.mappers;

import com.vanko.rentyservice.data.Apartment;
import com.vanko.rentyservice.data.Landlord;
import com.vanko.rentyservice.viewmodels.LandlordViewModel;
import org.springframework.stereotype.Service;
import com.vanko.rentyservice.business.interfaces.mappers.LandlordMapper;
import com.vanko.rentyservice.business.interfaces.mappers.ApartmentMapper;

import java.util.ArrayList;
import java.util.List;

@Service
public class LandlordMapperRenty implements LandlordMapper {
    private final ApartmentMapper apartmentMapper;

    public LandlordMapperRenty(ApartmentMapper apartmentMapper) {
        this.apartmentMapper = apartmentMapper;
    }

    @Override
    public LandlordViewModel mapLandlordToView(Landlord landlord) {
        LandlordViewModel landlordView = new LandlordViewModel();
        landlordView.setId(landlord.getId());
        landlordView.setFirstName(landlord.getFirstName());
        landlordView.setLastName(landlord.getLastName());
        landlordView.setEmail(landlord.getEmail());
        landlordView.setApartments(landlord.getApartments());

        return landlordView;
    }

    public Landlord mapLandlordFromView(LandlordViewModel landlordView, boolean mapApartments) {
        Landlord landlord = new Landlord();
        landlord.setId(landlordView.getId());
        landlord.setFirstName(landlordView.getFirstName());
        landlord.setLastName(landlordView.getLastName());
        landlord.setEmail(landlordView.getEmail());

        if (mapApartments) {
            if (landlordView.getApartments() == null) {
                throw new IllegalStateException("The landlord with id: " + landlordView.getId() + " has no apartments!");
            }

            List<Apartment> apartmentList = new ArrayList<>();
            landlordView.getApartments().forEach(apView -> {
                Apartment apartment = this.apartmentMapper.mapApartmentFromView(apView, landlord);
                apartmentList.add(apartment);
            });

            landlord.setApartments(apartmentList);
        }

        return landlord;
    }
}
