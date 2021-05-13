package br.com.bank.onlybank.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.io.Serializable;

import static br.com.bank.onlybank.constants.ValidationConstraints.MANDATORY;

public class DepositDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = MANDATORY)
    private Long bankId;

    @NotEmpty(message = MANDATORY)
    private String agencyNumber;

    @NotEmpty(message = MANDATORY)
    @Pattern(regexp = "\\d{7}-\\d{2}", message = "The account number must have the following format 00000-0 and be formed only by numbers!")
    private String accountNumber;

    @NotEmpty(message = MANDATORY)
    private String type;

    @Positive(message = "The value must be greater than zero")
    @NotNull(message = MANDATORY)
    private Double value;

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public String getAgencyNumber() {
        return agencyNumber;
    }

    public void setAgencyNumber(String agencyNumber) {
        this.agencyNumber = agencyNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
