package com.vanko.rentyservice.business.implementations.specifications;

import com.vanko.rentyservice.commonmodels.ApartmentType;
import com.vanko.rentyservice.data.Apartment;
import org.springframework.data.jpa.domain.Specification;

public class ApartmentSpecifications {
    public static Specification<Apartment> hasName(String name) {
        return ((root, query, cb) -> cb.like(root.get("name"), '%' + name + '%'));
    }

    public static Specification<Apartment> hasType(ApartmentType type) {
        return ((root, query, cb) -> cb.equal(root.get("type"), type));
    }

    public static Specification<Apartment> hasLandLord(long landlordId) {
        return ((root, query, cb) -> cb.equal(root.get("landlord").get("id"), landlordId));
    }
}
