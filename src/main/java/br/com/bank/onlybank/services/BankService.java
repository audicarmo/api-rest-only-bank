package br.com.bank.onlybank.services;

import br.com.bank.onlybank.dtos.BankDTO;
import br.com.bank.onlybank.entities.Bank;
import br.com.bank.onlybank.exceptions.OnlyBankException;
import br.com.bank.onlybank.repositories.BankRepository;
import br.com.bank.onlybank.services.impl.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BankService extends GenericServiceImpl<Bank, Long> {

    @Autowired
    private BankRepository bankRepository;

    public BankService(BankRepository bankRepository) {
        super(bankRepository);
        this.bankRepository = bankRepository;
    }

    @Override
    public Bank find(Long key) {
        Optional<Bank> bank = bankRepository.findById(key);

        return bank.orElseThrow(() -> new OnlyBankException(
                "Object not found! Id: " + key + ", Type: " + Bank.class.getName()));
    }

    @Override
    public Bank update(Bank newBank) {
        Bank bank = find(newBank.getId());
        newBank = update(bank, newBank);
        return super.update(newBank);
    }

    @Override
    protected Bank update(Bank entity, Bank newEntity) {
        Bank updateBank = new Bank();

        updateBank.setId(entity.getId());
        updateBank.setName(newEntity.getName());

        return updateBank;
    }

    @Override
    public void delete(Long key) {
        find(key);

        try {
            bankRepository.deleteById(key);
        } catch (OnlyBankException e) {
            throw new OnlyBankException("You cannot delete a bank that has branches!");
        }
    }

    public Bank convertsDtoToEntity(BankDTO dto) {
        return new Bank(dto.getId(), dto.getName());
    }
}
