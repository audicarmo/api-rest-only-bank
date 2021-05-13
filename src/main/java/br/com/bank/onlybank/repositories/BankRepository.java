package br.com.bank.onlybank.repositories;

import br.com.bank.onlybank.entities.Bank;
import br.com.bank.onlybank.repositories.custom.BankRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<Bank, Long>, BankRepositoryCustom {

}
