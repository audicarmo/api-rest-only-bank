package br.com.bank.onlybank.repositories.impl;

import br.com.bank.onlybank.entities.Client;
import br.com.bank.onlybank.repositories.custom.ClientRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Optional;

public class ClientRepositoryImpl implements ClientRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Client> findByCpf(String cpf) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Client> criteriaQuery = criteriaBuilder.createQuery(Client.class);

        Root<Client> root = criteriaQuery.from(Client.class);
        CriteriaQuery<Client> query = criteriaQuery.select(root);

        Predicate predicate = criteriaBuilder.equal(root.get("cpf"), cpf);

        Predicate[] predicates = {predicate};

        query.where(predicates);

        TypedQuery<Client> typedQuery = entityManager.createQuery(query);

        try {
            return Optional.of(typedQuery.getSingleResult());

        } catch (NoResultException e) {
            return null;
        }
    }
}
