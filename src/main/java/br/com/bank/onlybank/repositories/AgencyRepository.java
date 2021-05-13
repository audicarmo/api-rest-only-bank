package br.com.bank.onlybank.repositories;

import br.com.bank.onlybank.entities.Agency;
import br.com.bank.onlybank.repositories.custom.AgencyRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgencyRepository extends JpaRepository<Agency, Long>, AgencyRepositoryCustom {
}
