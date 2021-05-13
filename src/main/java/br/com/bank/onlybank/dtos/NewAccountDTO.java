package br.com.bank.onlybank.dtos;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import static br.com.bank.onlybank.constants.ValidationConstraints.CPF_IS_INVALID;
import static br.com.bank.onlybank.constants.ValidationConstraints.MANDATORY;
import static br.com.bank.onlybank.constants.ValidationConstraints.MAX_SIZE;
import static br.com.bank.onlybank.constants.ValidationConstraints.NUMBER_MIN_SIZE;

public class NewAccountDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = MANDATORY)
    private Long bankId;

    @NotEmpty(message = MANDATORY)
    private String agencyNumber;

    @NotEmpty(message = MANDATORY)
    @Pattern(regexp="\\d{7}-\\d{2}", message="The account number must have the following format 00000-0 and be formed only by numbers!")
    private String number;

    @NotEmpty(message = MANDATORY)
    private String type;

    @PositiveOrZero(message = "The balance must be greater than or equal to zero")
    @NotEmpty(message = MANDATORY)
    private Double balance;

    @NotEmpty(message = MANDATORY)
    @Length(min = NUMBER_MIN_SIZE, max = MAX_SIZE, message = "The length must be between 5 and 90 characters")
    private String name;

    @NotEmpty(message = MANDATORY)
    @CPF(message = CPF_IS_INVALID)
    private String cpf;

    @NotEmpty(message = MANDATORY)
    private String key;

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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
