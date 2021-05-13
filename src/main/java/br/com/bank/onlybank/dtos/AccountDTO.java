package br.com.bank.onlybank.dtos;

import br.com.bank.onlybank.entities.Account;

import javax.validation.constraints.Negative;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

import static br.com.bank.onlybank.constants.ValidationConstraints.MANDATORY;

public class AccountDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @NotEmpty(message = MANDATORY)
    @Pattern(regexp="\\d{5}-\\d{1}", message ="The account number must have the following format 00000-0 and be formed only by numbers!")
    private String number;

    @NotEmpty(message = MANDATORY)
    private String type;

    @Negative
    @NotNull(message = MANDATORY)
    private Double balance;

    private String bankName;

    private String agencyName;

    private String agencyNumber;

    public AccountDTO(Account account) {
        this.id = account.getId();
        this.number = account.getNumber();
        this.type = account.getType().name();
        this.balance = account.getBalance();

        init(account);
    }

    private void init(Account account) {
        if (account.getAgency() != null) {
            this.agencyName = account.getAgency().getName();
            this.agencyNumber = account.getAgency().getNumber();

            if (account.getAgency().getBank() != null) {
                this.bankName = account.getAgency().getBank().getName();
            }
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public String getAgencyNumber() {
        return agencyNumber;
    }

    public void setAgencyNumber(String agencyNumber) {
        this.agencyNumber = agencyNumber;
    }
}
