package com.vanko.rentyservice.business;
import com.vanko.rentyservice.business.implementations.ApartmentService;
import com.vanko.rentyservice.business.implementations.exceptions.ApartmentNotFoundException;
import com.vanko.rentyservice.business.interfaces.ILandlordService;
import com.vanko.rentyservice.business.interfaces.mappers.IApartmentMapper;
import com.vanko.rentyservice.business.testservices.ApartmentTestService;
import com.vanko.rentyservice.business.testservices.LandlordTestService;
import com.vanko.rentyservice.commonmodels.ApartmentType;
import com.vanko.rentyservice.data.Apartment;
import com.vanko.rentyservice.data.Landlord;
import com.vanko.rentyservice.viewmodels.ApartmentViewModel;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ApartmentServiceTests {
    @Mock
    private EntityManager entityManager;

    @Mock
    private ILandlordService landlordService;

    @Mock
    private IApartmentMapper apartmentMapper;

    @InjectMocks
    private ApartmentService apartmentService;

    private ApartmentTestService apartmentTestService = new ApartmentTestService();
    private LandlordTestService landlordTestService = new LandlordTestService();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetApartmentView() {
        Apartment apartment = this.apartmentTestService.getApartmentForTest(1, "apartment 1", ApartmentType.SINGLE_ROOM, null);
        ApartmentViewModel apartmentView = this.apartmentTestService.getApartmentView(1, "apartment 1", ApartmentType.SINGLE_ROOM);

        when(this.apartmentMapper.mapApartmentToView(eq(apartment))).thenReturn(apartmentView);
        when(this.entityManager.find(Apartment.class, apartment.getId())).thenReturn(apartment);

        ApartmentViewModel foundApartment = this.apartmentService.getApartmentView(apartment.getId());
        verify(this.entityManager).find(Apartment.class, apartment.getId());

        assertEquals(apartment.getId(), foundApartment.getId(), "ID of the found apartment doesn't match! ID: " + foundApartment.getId());
        assertEquals(apartment.getName(), foundApartment.getName(), "Name of the found apartment doesn't match! Name: " + foundApartment.getName());
        assertEquals(apartment.getType(), foundApartment.getType(), "Type of the found apartment doesn't match! Type: " + foundApartment.getType());
    }

    @Test
    void testAddApartment() {
        Landlord landlord = this.landlordTestService.getLandlordForTest(1, "Vanko", "Mihov", "vonko@gmail.com");
        ApartmentViewModel apartmentView = this.apartmentTestService.getApartmentView(1, "apartment 1", ApartmentType.SINGLE_ROOM);
        Apartment apartment = this.apartmentTestService.getApartmentForTest(1, "apartment 1", ApartmentType.SINGLE_ROOM, landlord);

        when(this.landlordService.getLandlord(anyLong())).thenReturn(landlord);
        when(this.apartmentMapper.mapApartmentFromView(eq(apartmentView), eq(landlord))).thenReturn(apartment);

        long newApId = this.apartmentService.addApartment(apartmentView, 1L);

        verify(this.entityManager).persist(apartment);
        assertEquals(apartment.getId(), newApId, "The id of the newly added apartment doesn't match! Found ID: " + newApId);
    }

    @Test
    void testGetApartmentViewNotFound() {
        when(entityManager.find(Apartment.class, 1L)).thenReturn(null);

        assertThrows(ApartmentNotFoundException.class, () -> apartmentService.getApartmentView(1L), "The test should have thrown ApartmentNotFoundException!");
    }
}
