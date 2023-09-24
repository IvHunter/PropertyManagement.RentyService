package com.vanko.rentyservice.business.implementations;

import com.vanko.rentyservice.business.implementations.exceptions.LandlordNotFoundException;
import com.vanko.rentyservice.business.interfaces.mappers.ILandlordMapper;
import com.vanko.rentyservice.data.Landlord;
import com.vanko.rentyservice.viewmodels.LandlordViewModel;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.vanko.rentyservice.business.interfaces.ILandlordService;

@Service
public class LandlordService implements ILandlordService {
    private final EntityManager entityManager;
    private final ILandlordMapper landlordMapper;

    public LandlordService(EntityManager entityManager, ILandlordMapper landlordMapper) {
        this.entityManager = entityManager;
        this.landlordMapper = landlordMapper;
    }

    @Override
    public LandlordViewModel getLandlordView(long id) {
        Landlord landlord = this.getLandlord(id);
        LandlordViewModel landlordView = this.landlordMapper.mapLandlordToView(landlord);

        return landlordView;
    }

    @Override
    @Transactional
    public long addLandlord(LandlordViewModel landlordView) {
        Landlord landlord = this.landlordMapper.mapLandlordFromView(landlordView, false);

        this.entityManager.persist(landlord);

        System.out.println("New Landlord ID: " + landlord.getId());

        return landlord.getId();
    }

    public Landlord getLandlord(long landlordId) {
        Landlord landlord = this.entityManager.find(Landlord.class, landlordId);
        if (landlord == null) {
            throw new LandlordNotFoundException("No landlord exists with id: " + landlordId);
        }

        return landlord;
    }
}
