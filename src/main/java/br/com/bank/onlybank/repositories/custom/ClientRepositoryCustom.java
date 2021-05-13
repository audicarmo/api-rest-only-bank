package br.com.bank.onlybank.repositories.custom;

import br.com.bank.onlybank.entities.Client;

import java.util.Optional;

public interface ClientRepositoryCustom {

    public Optional<Client> findByCpf(String cpf);
}
