package com.vanko.rentyservice.business.mappers;

import com.vanko.rentyservice.business.implementations.mappers.LandlordMapperRenty;
import com.vanko.rentyservice.business.testservices.LandlordTestService;
import com.vanko.rentyservice.commonmodels.ApartmentType;
import com.vanko.rentyservice.data.Apartment;
import com.vanko.rentyservice.data.Landlord;
import com.vanko.rentyservice.viewmodels.ApartmentViewModel;
import com.vanko.rentyservice.viewmodels.LandlordViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LandlordMapperTests {
    @InjectMocks
    private LandlordMapperRenty landlordMapper;

    private LandlordTestService landlordTestService = new LandlordTestService();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testMapLandlordToView() {
        Landlord landlord = this.landlordTestService.getLandlordForTest(1, "Vanko", "Mihov", "vonko@gmail.com");

        LandlordViewModel mappedLandlord = this.landlordMapper.mapLandlordToView(landlord);

        assertEquals(landlord.getId(), mappedLandlord.getId(), "ID of the found landlord doesn't match! ID: " + mappedLandlord.getId());
        assertEquals(landlord.getFirstName(), mappedLandlord.getFirstName(), "First Name of the found landlord doesn't match! First Name: " + mappedLandlord.getFirstName());
        assertEquals(landlord.getLastName(), mappedLandlord.getLastName(), "Last Name of the found landlord doesn't match! Last Name: " + mappedLandlord.getLastName());
        assertEquals(landlord.getEmail(), mappedLandlord.getEmail(), "Email of the found landlord doesn't match! Email: " + mappedLandlord.getEmail());
    }

    @Test
    void testMapLandlordFromView() {
        LandlordViewModel landlordView = this.landlordTestService.getLandlordViewForTest(1, "Vanko", "Mihov", "vonko@gmail.com");

        Landlord mappedLandlord = this.landlordMapper.mapLandlordFromView(landlordView, false);

        assertEquals(landlordView.getId(), mappedLandlord.getId(), "ID of the found landlord doesn't match! ID: " + mappedLandlord.getId());
        assertEquals(landlordView.getFirstName(), mappedLandlord.getFirstName(), "First Name of the found landlord doesn't match! First Name: " + mappedLandlord.getFirstName());
        assertEquals(landlordView.getLastName(), mappedLandlord.getLastName(), "Last Name of the found landlord doesn't match! Last Name: " + mappedLandlord.getLastName());
        assertEquals(landlordView.getEmail(), mappedLandlord.getEmail(), "Email of the found landlord doesn't match! Email: " + mappedLandlord.getEmail());
    }
}
