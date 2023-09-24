package com.vanko.rentyservice.business.mappers;

import com.vanko.rentyservice.business.implementations.mappers.ApartmentMapperRenty;
import com.vanko.rentyservice.business.implementations.mappers.LandlordMapperRenty;
import com.vanko.rentyservice.business.testservices.ApartmentTestService;
import com.vanko.rentyservice.business.testservices.LandlordTestService;
import com.vanko.rentyservice.commonmodels.ApartmentType;
import com.vanko.rentyservice.data.Landlord;
import com.vanko.rentyservice.viewmodels.ApartmentDto;
import com.vanko.rentyservice.viewmodels.LandlordDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LandlordMapperTests {
    private ApartmentMapperRenty apartmentMapper = new ApartmentMapperRenty();

    @InjectMocks
    private LandlordMapperRenty landlordMapper = new LandlordMapperRenty(this.apartmentMapper);

    private LandlordTestService landlordTestService = new LandlordTestService();

    private ApartmentTestService apartmentTestService = new ApartmentTestService();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testMapLandlordToView() {
        Landlord landlord = this.landlordTestService.getLandlordForTest(1, "Vanko", "Mihov", "vonko@gmail.com");

        LandlordDto mappedLandlord = this.landlordMapper.mapLandlordToView(landlord);

        assertEquals(landlord.getId(), mappedLandlord.getId(), "ID of the found landlord doesn't match! ID: " + mappedLandlord.getId());
        assertEquals(landlord.getFirstName(), mappedLandlord.getFirstName(), "First Name of the found landlord doesn't match! First Name: " + mappedLandlord.getFirstName());
        assertEquals(landlord.getLastName(), mappedLandlord.getLastName(), "Last Name of the found landlord doesn't match! Last Name: " + mappedLandlord.getLastName());
        assertEquals(landlord.getEmail(), mappedLandlord.getEmail(), "Email of the found landlord doesn't match! Email: " + mappedLandlord.getEmail());
    }

    @Test
    void testMapLandlordFromView() {
        LandlordDto landlordView = this.landlordTestService.getLandlordViewForTest(1, "Vanko", "Mihov", "vonko@gmail.com");

        Landlord mappedLandlord = this.landlordMapper.mapLandlordFromView(landlordView, false);

        assertEquals(landlordView.getId(), mappedLandlord.getId(), "ID of the found landlord doesn't match! ID: " + mappedLandlord.getId());
        assertEquals(landlordView.getFirstName(), mappedLandlord.getFirstName(), "First Name of the found landlord doesn't match! First Name: " + mappedLandlord.getFirstName());
        assertEquals(landlordView.getLastName(), mappedLandlord.getLastName(), "Last Name of the found landlord doesn't match! Last Name: " + mappedLandlord.getLastName());
        assertEquals(landlordView.getEmail(), mappedLandlord.getEmail(), "Email of the found landlord doesn't match! Email: " + mappedLandlord.getEmail());
    }

    @Test
    void testMapLandlordWithApartmentsFromView() {
        LandlordDto landlordView = this.landlordTestService.getLandlordViewForTest(1, "Vanko", "Mihov", "vonko@gmail.com");
        ApartmentDto apartment1 = this.apartmentTestService.getApartmentViewForTest(1, "apartment 1", ApartmentType.SINGLE_ROOM);
        ApartmentDto apartment2 = this.apartmentTestService.getApartmentViewForTest(2, "apartment 2", ApartmentType.DOUBLE_ROOM);
        landlordView.setApartments(new ArrayList<>(Arrays.asList(apartment1, apartment2 )));

        Landlord mappedLandlord = this.landlordMapper.mapLandlordFromView(landlordView, true);

        assertEquals(landlordView.getId(), mappedLandlord.getId(), "ID of the found landlord doesn't match! ID: " + mappedLandlord.getId());
        assertEquals(landlordView.getFirstName(), mappedLandlord.getFirstName(), "First Name of the found landlord doesn't match! First Name: " + mappedLandlord.getFirstName());
        assertEquals(landlordView.getLastName(), mappedLandlord.getLastName(), "Last Name of the found landlord doesn't match! Last Name: " + mappedLandlord.getLastName());
        assertEquals(landlordView.getEmail(), mappedLandlord.getEmail(), "Email of the found landlord doesn't match! Email: " + mappedLandlord.getEmail());
        assertEquals(2, landlordView.getApartments().size(), "Landlord's appartments count doesn't match! Email: " + mappedLandlord.getApartments().size());
        landlordView.getApartments().stream().anyMatch(ap -> ap.getId() == 1 && ap.getName() == "apartment 1" && ap.getType() == ApartmentType.SINGLE_ROOM);
        landlordView.getApartments().stream().anyMatch(ap -> ap.getId() == 2 && ap.getName() == "apartment 2" && ap.getType() == ApartmentType.DOUBLE_ROOM);
    }
}
