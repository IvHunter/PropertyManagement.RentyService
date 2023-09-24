package com.vanko.rentyservice.business.interfaces.mappers;

import com.vanko.rentyservice.data.Landlord;
import com.vanko.rentyservice.viewmodels.LandlordViewModel;

public interface ILandlordMapper {
    LandlordViewModel mapLandlordToView(Landlord landlord);

    Landlord mapLandlordFromView(LandlordViewModel landlordView, boolean mapApartments);
}
