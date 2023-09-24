package com.vanko.rentyservice.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class CriteriaObjects<T> {
    private CriteriaBuilder cb;
    private CriteriaQuery<T> query;
    private Root<T> root;

    public CriteriaObjects(EntityManager entityManager, Class<T> type) {
        this.cb = entityManager.getCriteriaBuilder();
        this.query = cb.createQuery(type);
        this.root = query.from(type);
    }

    public CriteriaBuilder getCb() {
        return cb;
    }

    public CriteriaQuery<T> getQuery() {
        return query;
    }

    public Root<T> getRoot() {
        return root;
    }
}
