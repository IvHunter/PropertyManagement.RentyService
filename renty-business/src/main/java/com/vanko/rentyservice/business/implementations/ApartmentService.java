package com.vanko.rentyservice.business.implementations;

import com.vanko.rentyservice.business.implementations.exceptions.ApartmentNotFoundException;
import com.vanko.rentyservice.business.interfaces.mappers.IApartmentMapper;
import com.vanko.rentyservice.data.Apartment;
import com.vanko.rentyservice.data.Landlord;
import com.vanko.rentyservice.viewmodels.ApartmentViewModel;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.vanko.rentyservice.business.interfaces.IApartmentService;
import com.vanko.rentyservice.business.interfaces.ILandlordService;

@Service
public class ApartmentService implements IApartmentService {
    private final EntityManager entityManager;
    private final ILandlordService landlordService;
    private final IApartmentMapper apartmentMapper;

    public ApartmentService(EntityManager entityManager,
                            ILandlordService landlordService,
                            IApartmentMapper apartmentMapper) {
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
