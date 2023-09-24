package com.vanko.rentyservice.business.implementations;

import com.vanko.rentyservice.business.implementations.exceptions.ApartmentNotFoundException;
import com.vanko.rentyservice.business.interfaces.mappers.ApartmentMapper;
import com.vanko.rentyservice.data.Apartment;
import com.vanko.rentyservice.data.Landlord;
import com.vanko.rentyservice.viewmodels.ApartmentViewModel;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.vanko.rentyservice.business.interfaces.ApartmentService;
import com.vanko.rentyservice.business.interfaces.LandlordService;

@Service
public class ApartmentServiceRenty implements ApartmentService {
    private final EntityManager entityManager;
    private final LandlordService landlordService;
    private final ApartmentMapper apartmentMapper;

    public ApartmentServiceRenty(EntityManager entityManager,
                                 LandlordService landlordService,
                                 ApartmentMapper apartmentMapper) {
        this.entityManager = entityManager;
        this.landlordService = landlordService;
        this.apartmentMapper = apartmentMapper;
    }

    @Override
    @Transactional
    public long addApartment(ApartmentViewModel apartmentView, long landlordId) {
        Landlord landlord = this.landlordService.getLandlord(landlordId);
        Apartment apartment = this.apartmentMapper.mapApartmentFromView(apartmentView, landlord);

        this.entityManager.persist(apartment);

        System.out.println("New apartment ID: " + apartment.getId());

        return apartment.getId();
    }

    public ApartmentViewModel getApartmentView(long id) {
        var apartment = this.entityManager.find(Apartment.class, id);
        if (apartment == null) {
            throw new ApartmentNotFoundException("No apartment exists with id: " + id);
        }

        return this.apartmentMapper.mapApartmentToView(apartment);
    }
}
