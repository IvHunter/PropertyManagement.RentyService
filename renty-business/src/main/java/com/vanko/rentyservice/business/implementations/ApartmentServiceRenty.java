package com.vanko.rentyservice.business.implementations;

import com.vanko.rentyservice.business.implementations.exceptions.ApartmentNotFoundException;
import com.vanko.rentyservice.business.interfaces.mappers.ApartmentMapper;
import com.vanko.rentyservice.data.Apartment;
import com.vanko.rentyservice.data.Landlord;
import com.vanko.rentyservice.viewmodels.ApartmentViewModel;
import com.vanko.rentyservice.viewmodels.SellApartmentRequestModel;
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
        Landlord landlord = this.landlordService.getLandlord(landlordId, false);
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

    @Override
    @Transactional
    public long sellApartment(SellApartmentRequestModel request) {
        var apartment = this.entityManager.find(Apartment.class, request.getApartmentId());
        if (apartment == null) {
            throw new ApartmentNotFoundException("No apartment exists with id: " + request.getApartmentId());
        }

        //TODO: Add payment logic

        Landlord landlord = this.landlordService.getLandlord(request.getNewLandlordId(), false);
        apartment.setLandlord(landlord);

        this.entityManager.merge(apartment);

        return apartment.getId();
    }
}
