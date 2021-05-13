package br.com.bank.onlybank.services;

import br.com.bank.onlybank.entities.Account;
import br.com.bank.onlybank.enums.Profile;
import br.com.bank.onlybank.exceptions.OnlyBankException;
import br.com.bank.onlybank.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private AccountService accountService;

    public UserDetailsImpl getUserDetails() {
        try {
            return (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean validateClientAccount(Account account) {
        UserDetailsImpl user = getUserDetails();

        if (user == null || !user.hasRole(Profile.ADMIN) && !account.getClient().equals(user.getClient())) {
            throw new OnlyBankException("Acesso negado");
        }
        return true;
    }

    public boolean validateClientAccount(Long key) {
        return validateClientAccount(accountService.find(key));
    }

    public void validateClientId(Long key) {
        UserDetailsImpl user = getUserDetails();

        if (user == null || !user.hasRole(Profile.ADMIN) && !key.equals(user.getId())) {
            throw new OnlyBankException("Acesso negado");
        }
    }

    public void validateClientCpf(String cpf) {
        UserDetailsImpl user = getUserDetails();

        if (user == null || !user.hasRole(Profile.ADMIN) && !cpf.equals(user.getUsername())) {
            throw new OnlyBankException("Acesso negado");
        }
    }

    public boolean hasRole(Profile profile) {
        UserDetailsImpl user = getUserDetails();

        if (user == null) {
            throw new OnlyBankException("Acesso negado");
        }

        return user.hasRole(profile);
    }
}
