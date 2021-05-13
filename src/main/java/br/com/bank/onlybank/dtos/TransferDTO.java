package br.com.bank.onlybank.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

import static br.com.bank.onlybank.constants.ValidationConstraints.MANDATORY;

public class TransferDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = MANDATORY)
    private Long bankOriginId;

    @NotEmpty(message = MANDATORY)
    private String agencyOriginNumber;

    @NotEmpty(message = MANDATORY)
    @Pattern(regexp = "\\d{7}-\\d{2}", message = "The account number must have the following format 00000-0 and be formed only by numbers!")
    private String accountOriginNumber;

    @NotEmpty(message = MANDATORY)
    private String typeOrigin;

    @NotNull(message = MANDATORY)
    private Long bankDestinyId;

    @NotNull(message = MANDATORY)
    private String agencyDestinyNumber;

    @NotEmpty(message = MANDATORY)
    @Pattern(regexp = "\\d{7}-\\d{2}", message = "The account number must have the following format 00000-0 and be formed only by numbers!")
    private String accountDestinyNumber;

    @NotEmpty(message = MANDATORY)
    private String typeDestiny;

   private Double value;

    public Long getBankOriginId() {
        return bankOriginId;
    }

    public void setBankOriginId(Long bankOrigemId) {
        this.bankOriginId = bankOrigemId;
    }

    public String getAgencyOriginNumber() {
        return agencyOriginNumber;
    }

    public void setAgencyOriginNumber(String agencyOriginNumber) {
        this.agencyOriginNumber = agencyOriginNumber;
    }

    public String getAccountOriginNumber() {
        return accountOriginNumber;
    }

    public void setAccountOriginNumber(String accountOriginNumber) {
        this.accountOriginNumber = accountOriginNumber;
    }

    public String getTypeOrigin() {
        return typeOrigin;
    }

    public void setTypeOrigin(String typeOrigin) {
        this.typeOrigin = typeOrigin;
    }

    public Long getBankDestinyId() {
        return bankDestinyId;
    }

    public void setBankDestinyId(Long bankDestinyId) {
        this.bankDestinyId = bankDestinyId;
    }

    public String getAgencyDestinyNumber() {
        return agencyDestinyNumber;
    }

    public void setAgencyDestinyNumber(String agencyDestinyNumber) {
        this.agencyDestinyNumber = agencyDestinyNumber;
    }

    public String getAccountDestinyNumber() {
        return accountDestinyNumber;
    }

    public void setAccountDestinyNumber(String accountDestinyNumber) {
        this.accountDestinyNumber = accountDestinyNumber;
    }

    public String getTypeDestiny() {
        return typeDestiny;
    }

    public void setTypeDestiny(String typeDestiny) {
        this.typeDestiny = typeDestiny;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
