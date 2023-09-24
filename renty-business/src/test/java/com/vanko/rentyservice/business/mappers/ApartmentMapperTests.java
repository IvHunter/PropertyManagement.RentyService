package com.vanko.rentyservice.business.mappers;

import com.vanko.rentyservice.business.implementations.mappers.ApartmentMapperRenty;
import com.vanko.rentyservice.business.testservices.ApartmentTestService;
import com.vanko.rentyservice.business.testservices.LandlordTestService;
import com.vanko.rentyservice.commonmodels.ApartmentType;
import com.vanko.rentyservice.data.Apartment;
import com.vanko.rentyservice.data.Landlord;
import com.vanko.rentyservice.viewmodels.ApartmentDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApartmentMapperTests {
    @InjectMocks
    private ApartmentMapperRenty apartmentMapper;

    private ApartmentTestService apartmentTestService = new ApartmentTestService();

    private LandlordTestService landlordTestService = new LandlordTestService();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testMapApartmentToView() {
        Apartment apartment = this.apartmentTestService.getApartmentForTest(1, "apartment 1", ApartmentType.SINGLE_ROOM, null);

        ApartmentDto mappedApartment = this.apartmentMapper.mapApartmentToView(apartment);

        assertEquals(apartment.getId(), mappedApartment.getId(), "ID of the found apartment doesn't match! ID: " + mappedApartment.getId());
        assertEquals(apartment.getName(), mappedApartment.getName(), "Name of the found apartment doesn't match! Name: " + mappedApartment.getName());
        assertEquals(apartment.getType(), mappedApartment.getType(), "Type of the found apartment doesn't match! Type: " + mappedApartment.getType());
    }

    @Test
    void testMapApartmentFromView() {
        ApartmentDto apartmentView = this.apartmentTestService.getApartmentViewForTest(1, "apartment 1", ApartmentType.SINGLE_ROOM);
        Landlord landlord = this.landlordTestService.getLandlordForTest(1, "Vanko", "Mihov", "vonko@gmail.com");

        Apartment mappedApartment = this.apartmentMapper.mapApartmentFromView(apartmentView, landlord);

        assertEquals(mappedApartment.getId(), mappedApartment.getId(), "ID of the found apartment doesn't match! ID: " + mappedApartment.getId());
        assertEquals(mappedApartment.getName(), mappedApartment.getName(), "Name of the found apartment doesn't match! Name: " + mappedApartment.getName());
        assertEquals(mappedApartment.getType(), mappedApartment.getType(), "Type of the found apartment doesn't match! Type: " + mappedApartment.getType());
    }
}
