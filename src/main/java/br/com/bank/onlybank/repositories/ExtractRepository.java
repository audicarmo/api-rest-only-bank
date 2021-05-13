package br.com.bank.onlybank.repositories;

import br.com.bank.onlybank.entities.Extract;
import br.com.bank.onlybank.repositories.custom.ExtractRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExtractRepository extends JpaRepository<Extract, Long>, ExtractRepositoryCustom {

}
