package com.vanko.rentyservice.business.implementations;

import com.vanko.rentyservice.business.implementations.exceptions.ApartmentNotFoundException;
import com.vanko.rentyservice.business.implementations.specifications.ApartmentSpecifications;
import com.vanko.rentyservice.business.interfaces.mappers.ApartmentMapper;
import com.vanko.rentyservice.data.Apartment;
import com.vanko.rentyservice.data.Landlord;
import com.vanko.rentyservice.viewmodels.ApartmentFiltersDto;
import com.vanko.rentyservice.viewmodels.ApartmentDto;
import com.vanko.rentyservice.viewmodels.SellApartmentDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.vanko.rentyservice.business.interfaces.ApartmentService;
import com.vanko.rentyservice.business.interfaces.LandlordService;

import java.util.List;

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
    public long addApartment(ApartmentDto apartmentView, long landlordId) {
        Landlord landlord = this.landlordService.getLandlord(landlordId, false);
        Apartment apartment = this.apartmentMapper.mapApartmentFromView(apartmentView, landlord);

        this.entityManager.persist(apartment);

        System.out.println("New apartment ID: " + apartment.getId());

        return apartment.getId();
    }

    public ApartmentDto getApartmentView(long id) {
        var apartment = this.entityManager.find(Apartment.class, id);
        if (apartment == null) {
            throw new ApartmentNotFoundException("No apartment exists with id: " + id);
        }

        return this.apartmentMapper.mapApartmentToView(apartment);
    }

    public List<ApartmentDto> getApartmentsByFilters(ApartmentFiltersDto filters) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Apartment> query = cb.createQuery(Apartment.class);
        Root<Apartment> root = query.from(Apartment.class);

        Specification<Apartment> spec = this.buildFiltersSpecification(filters);

        query.where(spec.toPredicate(root, query, cb));

        List<ApartmentDto> apartments = entityManager.createQuery(query).getResultList()
                .stream().map(ap -> this.apartmentMapper.mapApartmentToView(ap)).toList();

        return apartments;
    }

    @Override
    @Transactional
    public long sellApartment(SellApartmentDto request) {
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

    private Specification<Apartment> buildFiltersSpecification(ApartmentFiltersDto filters) {
        Specification<Apartment> spec = Specification.where(null);

        if (filters.getName() != null) {
            spec = spec.and(ApartmentSpecifications.hasName(filters.getName()));
        }
        if (filters.getType() != null) {
            spec = spec.and(ApartmentSpecifications.hasType(filters.getType()));
        }
        if (filters.getLandlordId() > 0) {
            spec = spec.and(ApartmentSpecifications.hasLandLord(filters.getLandlordId()));
        }

        return spec;
    }
}
