package br.com.bank.onlybank.repositories.impl;

import br.com.bank.onlybank.entities.Agency;
import br.com.bank.onlybank.exceptions.OnlyBankException;
import br.com.bank.onlybank.repositories.custom.AgencyRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

public class AgencyRepositoryCustomImpl implements AgencyRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    public Optional<Agency> findBYAgency(Long bankId, String accountNumber) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Agency> cq = cb.createQuery(Agency.class);

        Root<Agency> root = cq.from(Agency.class);
        CriteriaQuery<Agency> query = cq.select(root);

        Predicate predicateBankId = cb.equal(root.get("bank").get("id"), bankId);
        Predicate predicate = cb.equal(root.get("number"), accountNumber);

        Predicate[] predicates = { predicateBankId, predicate };

        query.where(predicates);

        TypedQuery<Agency> tq = entityManager.createQuery(query);

        try {
            return Optional.of(tq.getSingleResult());
        } catch (OnlyBankException e) {
            return null;
        }
    }

    @Override
    public Optional<Agency> findByAgency(Long bankId, String AccountNumber) {
        return Optional.empty();
    }

    @Override
    public List<Agency> findNumber(Long id) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Agency> cq = cb.createQuery(Agency.class);

        Root<Agency> root = cq.from(Agency.class);
        CriteriaQuery<Agency> query = cq.select(root);

        Predicate predicateBankId = cb.equal(root.get("bank").get("id"), id);
        query.where(predicateBankId);

        TypedQuery<Agency> tq = entityManager.createQuery(query);

        return tq.getResultList();
    }
}
