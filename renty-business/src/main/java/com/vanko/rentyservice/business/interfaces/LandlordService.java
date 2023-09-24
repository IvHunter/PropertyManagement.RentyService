package com.vanko.rentyservice.business.interfaces;

import com.vanko.rentyservice.data.Landlord;
import com.vanko.rentyservice.viewmodels.LandlordViewModel;

public interface LandlordService {
    Landlord getLandlord(long landlordId, boolean withApartments);

    LandlordViewModel getLandlordView(long id, boolean withApartments);

    long addLandlord(LandlordViewModel landlord);
}
