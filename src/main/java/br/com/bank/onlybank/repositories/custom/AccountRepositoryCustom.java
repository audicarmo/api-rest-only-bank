package br.com.bank.onlybank.repositories.custom;

import br.com.bank.onlybank.entities.Account;
import br.com.bank.onlybank.entities.Client;

import java.util.List;
import java.util.Optional;

public interface AccountRepositoryCustom {

    public Optional<Account> find(String number);
    public Optional<Account> findNumber(String accountNumber, String agencyNumber);
    public Optional<Account> findNumberAgency(String accountNumber, String agencyNumber, Long bankId);
    public Optional<List<Account>> findByClient(Client client);
}
