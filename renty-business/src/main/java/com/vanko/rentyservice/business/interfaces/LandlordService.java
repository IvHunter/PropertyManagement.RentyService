package com.vanko.rentyservice.business.interfaces;

import com.vanko.rentyservice.data.Landlord;
import com.vanko.rentyservice.viewmodels.LandlordDto;

public interface LandlordService {
    Landlord getLandlord(long landlordId, boolean withApartments);

    LandlordDto getLandlordView(long id, boolean withApartments);

    long addLandlord(LandlordDto landlord);
}
