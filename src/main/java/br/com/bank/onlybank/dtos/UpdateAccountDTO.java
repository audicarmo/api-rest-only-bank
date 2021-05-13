package br.com.bank.onlybank.dtos;

import br.com.bank.onlybank.entities.Account;

import javax.validation.constraints.Negative;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

import static br.com.bank.onlybank.constants.ValidationConstraints.MANDATORY;

public class UpdateAccountDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotEmpty(message = MANDATORY)
    @Pattern(regexp="\\d{5}-\\d{1}", message = "The account number must have the following format 00000-0 and be formed only by numbers!")
    private String number;

    @NotEmpty(message = MANDATORY)
    private String type;

    @Negative
    @NotNull(message = MANDATORY)
    private Double balance;

    public UpdateAccountDTO(Account account) {
        this.number = account.getNumber();
        this.type = account.getType().name();
        this.balance = account.getBalance();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
