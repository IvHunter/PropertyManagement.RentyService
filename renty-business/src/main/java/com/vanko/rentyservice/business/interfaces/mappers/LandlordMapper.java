package com.vanko.rentyservice.business.interfaces.mappers;

import com.vanko.rentyservice.data.Landlord;
import com.vanko.rentyservice.viewmodels.LandlordDto;

public interface LandlordMapper {
    LandlordDto mapLandlordToView(Landlord landlord);

    Landlord mapLandlordFromView(LandlordDto landlordView, boolean mapApartments);
}
