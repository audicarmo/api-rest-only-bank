package br.com.bank.onlybank.repositories;

import br.com.bank.onlybank.entities.Client;
import br.com.bank.onlybank.repositories.custom.ClientRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long>, ClientRepositoryCustom {

}
