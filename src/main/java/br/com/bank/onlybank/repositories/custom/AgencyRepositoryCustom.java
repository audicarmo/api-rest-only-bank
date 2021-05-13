package br.com.bank.onlybank.repositories.custom;

import br.com.bank.onlybank.entities.Agency;

import java.util.List;
import java.util.Optional;

public interface AgencyRepositoryCustom {

    public Optional<Agency> findByAgency(Long bankId, String AccountNumber);
    public List<Agency> findNumber(Long id);
}
