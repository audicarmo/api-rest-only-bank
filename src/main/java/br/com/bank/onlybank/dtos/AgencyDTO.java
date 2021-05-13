package br.com.bank.onlybank.dtos;

import br.com.bank.onlybank.entities.Agency;
import br.com.bank.onlybank.entities.Bank;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

import static br.com.bank.onlybank.constants.ValidationConstraints.MANDATORY;
import static br.com.bank.onlybank.constants.ValidationConstraints.MAX_SIZE;
import static br.com.bank.onlybank.constants.ValidationConstraints.NUMBER_MIN_SIZE;

public class AgencyDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotEmpty(message = MANDATORY)
    @Pattern(regexp="\\d{7}-\\d{2}", message="The agency number must have the following format 0000-0 and be formed only by numbers!")
    private String number;

    @NotEmpty(message = MANDATORY)
    @Length(min=NUMBER_MIN_SIZE, max=MAX_SIZE, message="The length must be between 3 and 90 characters")
    private String name;

    private Bank bank;

    public AgencyDTO(Agency agency) {
        this.number = agency.getNumber();
        this.name = agency.getName();
        this.bank = agency.getBank();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }
}
