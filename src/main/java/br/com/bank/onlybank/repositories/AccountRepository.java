package br.com.bank.onlybank.repositories;

import br.com.bank.onlybank.entities.Account;
import br.com.bank.onlybank.entities.Client;
import br.com.bank.onlybank.repositories.custom.AccountRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long>, AccountRepositoryCustom {

    Page<Account> findByClient(Client client, PageRequest pageRequest);
}
