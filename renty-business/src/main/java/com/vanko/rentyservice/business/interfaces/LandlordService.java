package com.vanko.rentyservice.business.interfaces;

import com.vanko.rentyservice.data.Landlord;
import com.vanko.rentyservice.viewmodels.LandlordViewModel;

public interface LandlordService {
    long addLandlord(LandlordViewModel landlord);

    LandlordViewModel getLandlordView(long id);

    Landlord getLandlord(long landlordId);
}
