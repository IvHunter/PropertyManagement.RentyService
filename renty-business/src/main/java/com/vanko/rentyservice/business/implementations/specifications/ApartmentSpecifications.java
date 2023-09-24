package com.vanko.rentyservice.business.implementations.specifications;

import com.vanko.rentyservice.data.Apartment;
import org.springframework.data.jpa.domain.Specification;

public class ApartmentSpecifications {
    public static Specification<Apartment> hasName(String name) {
        return ((root, query, cb) -> cb.like(root.get("name"), '%' + name + '%'));
    }
}
