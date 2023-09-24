package com.vanko.rentyservice.business.implementations;

import com.vanko.rentyservice.business.implementations.exceptions.LandlordNotFoundException;
import com.vanko.rentyservice.business.interfaces.mappers.LandlordMapper;
import com.vanko.rentyservice.data.Landlord;
import com.vanko.rentyservice.viewmodels.LandlordDto;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.vanko.rentyservice.business.interfaces.LandlordService;

@Service
public class LandlordServiceRenty implements LandlordService {
    private final EntityManager entityManager;
    private final LandlordMapper landlordMapper;

    public LandlordServiceRenty(EntityManager entityManager, LandlordMapper landlordMapper) {
        this.entityManager = entityManager;
        this.landlordMapper = landlordMapper;
    }

    public Landlord getLandlord(long landlordId, boolean withApartments) {
        Landlord landlord = this.entityManager.find(Landlord.class, landlordId);
        if (landlord == null) {
            throw new LandlordNotFoundException("No landlord exists with id: " + landlordId);
        }

        if (withApartments) {
            landlord.getApartments().size();
        }

        return landlord;
    }

    @Override
    public LandlordDto getLandlordView(long id, boolean withApartments) {
        Landlord landlord = this.getLandlord(id, withApartments);
        LandlordDto landlordView = this.landlordMapper.mapLandlordToView(landlord);

        return landlordView;
    }

    @Override
    @Transactional
    public long addLandlord(LandlordDto landlordView) {
        Landlord landlord = this.landlordMapper.mapLandlordFromView(landlordView, false);

        this.entityManager.persist(landlord);

        System.out.println("New Landlord ID: " + landlord.getId());

        return landlord.getId();
    }
}
