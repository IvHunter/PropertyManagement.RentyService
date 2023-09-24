package com.vanko.rentyservice.business;

import com.vanko.rentyservice.business.implementations.LandlordServiceRenty;
import com.vanko.rentyservice.business.testservices.LandlordTestService;
import com.vanko.rentyservice.business.interfaces.mappers.LandlordMapper;
import com.vanko.rentyservice.data.Landlord;
import com.vanko.rentyservice.viewmodels.LandlordViewModel;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LandlordServiceRentyTests {
    @Mock
    private EntityManager entityManager;

    @Mock
    private LandlordMapper landlordMapper;

    @InjectMocks
    private LandlordServiceRenty landlordServiceRenty;

    private LandlordTestService landlordTestService = new LandlordTestService();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetLandlord() {
        Landlord landlord = this.landlordTestService.getLandlordForTest(1, "Vanko", "Mihov", "vonko@gmail.com");

        when(this.entityManager.find(Landlord.class, landlord.getId())).thenReturn(landlord);

        Landlord foundLandlord = this.landlordServiceRenty.getLandlord(landlord.getId(), false);
        verify(this.entityManager).find(Landlord.class, landlord.getId());

        assertEquals(landlord.getId(), foundLandlord.getId(), "ID of the found landlord doesn't match! ID: " + foundLandlord.getId());
        assertEquals(landlord.getFirstName(), foundLandlord.getFirstName(), "First Name of the found landlord doesn't match! First Name: " + foundLandlord.getFirstName());
        assertEquals(landlord.getLastName(), foundLandlord.getLastName(), "Last Name of the found landlord doesn't match! Last Name: " + foundLandlord.getLastName());
        assertEquals(landlord.getEmail(), foundLandlord.getEmail(), "Email of the found landlord doesn't match! Email: " + foundLandlord.getEmail());
    }

    @Test
    void testGetLandlordView() {
        Landlord landlord = this.landlordTestService.getLandlordForTest(1, "Vanko", "Mihov", "vonko@gmail.com");
        LandlordViewModel landlordView = this.landlordTestService.getLandlordViewForTest(1, "Vanko", "Mihov", "vonko@gmail.com");

        when(this.landlordMapper.mapLandlordToView(eq(landlord))).thenReturn(landlordView);
        when(this.entityManager.find(Landlord.class, landlord.getId())).thenReturn(landlord);

        LandlordViewModel foundLandlordView = this.landlordServiceRenty.getLandlordView(landlord.getId(), false);
        verify(this.entityManager).find(Landlord.class, landlord.getId());

        assertEquals(landlord.getId(), foundLandlordView.getId(), "ID of the found landlord doesn't match! ID: " + foundLandlordView.getId());
        assertEquals(landlord.getFirstName(), foundLandlordView.getFirstName(), "First Name of the found landlord doesn't match! First Name: " + foundLandlordView.getFirstName());
        assertEquals(landlord.getLastName(), foundLandlordView.getLastName(), "Last Name of the found landlord doesn't match! Last Name: " + foundLandlordView.getLastName());
        assertEquals(landlord.getEmail(), foundLandlordView.getEmail(), "Email of the found landlord doesn't match! Email: " + foundLandlordView.getEmail());
    }

    @Test
    void testAddLandlord() {
        Landlord landlord = this.landlordTestService.getLandlordForTest(1, "Vanko", "Mihov", "vonko@gmail.com");
        LandlordViewModel landlordView = this.landlordTestService.getLandlordViewForTest(1, "Vanko", "Mihov", "vonko@gmail.com");

        when(this.landlordMapper.mapLandlordFromView(eq(landlordView), eq(false))).thenReturn(landlord);

        long newLandlordId = this.landlordServiceRenty.addLandlord(landlordView);

        verify(this.entityManager).persist(landlord);
        assertEquals(landlord.getId(), newLandlordId, "The id of the newly added landlord doesn't match! Found ID: " + newLandlordId);
    }
}
