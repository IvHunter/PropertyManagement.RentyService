package com.vanko.rentyservice.business.testservices;

import com.vanko.rentyservice.data.Landlord;
import com.vanko.rentyservice.viewmodels.LandlordViewModel;
import org.springframework.stereotype.Service;

@Service
public class LandlordTestService {
    public Landlord getLandlordForTest(long landlordId, String firstName, String lastName, String email) {
        Landlord landlord = new Landlord(); //populate it
        landlord.setId(landlordId);
        landlord.setFirstName(firstName);
        landlord.setLastName(lastName);
        landlord.setEmail(email);

        return landlord;
    }

    public LandlordViewModel getLandlordViewForTest(long landlordId, String firstName, String lastName, String email) {
        LandlordViewModel landlordView = new LandlordViewModel(); //populate it
        landlordView.setId(landlordId);
        landlordView.setFirstName(firstName);
        landlordView.setLastName(lastName);
        landlordView.setEmail(email);

        return landlordView;
    }
}
