package com.vanko.rentyservice.business.testservices;

import com.vanko.rentyservice.commonmodels.ApartmentType;
import com.vanko.rentyservice.data.Apartment;
import com.vanko.rentyservice.data.Landlord;
import com.vanko.rentyservice.viewmodels.ApartmentDto;
import org.springframework.stereotype.Service;

@Service
public class ApartmentTestService {
    public ApartmentDto getApartmentViewForTest(long apartmentId, String apartmentName, ApartmentType type) {
        ApartmentDto apartmentView = new ApartmentDto(); //populate it
        apartmentView.setId(apartmentId);
        apartmentView.setName(apartmentName);
        apartmentView.setType(ApartmentType.DOUBLE_ROOM);

        return apartmentView;
    }

    public Apartment getApartmentForTest(long apartmentId, String apartmentName, ApartmentType type, Landlord landlord) {
        Apartment apartment = new Apartment(); //populate it
        apartment.setId(apartmentId);
        apartment.setName(apartmentName);
        apartment.setType(ApartmentType.DOUBLE_ROOM);
        apartment.setLandlord(landlord);

        return apartment;
    }
}
