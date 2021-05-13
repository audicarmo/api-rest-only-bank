package br.com.bank.onlybank.repositories.custom;

import br.com.bank.onlybank.entities.Extract;

import java.util.List;

public interface ExtractRepositoryCustom {

    public List<Extract> listallbyaccount(Long id);
}
