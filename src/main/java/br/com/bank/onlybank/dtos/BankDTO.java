package br.com.bank.onlybank.dtos;

import br.com.bank.onlybank.entities.Bank;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

import static br.com.bank.onlybank.constants.ValidationConstraints.MANDATORY;
import static br.com.bank.onlybank.constants.ValidationConstraints.MAX_SIZE;
import static br.com.bank.onlybank.constants.ValidationConstraints.NUMBER_MIN_SIZE;

public class BankDTO implements Serializable  {

    private static final long serialVersionUID = 1L;

    private Long id;

    @NotEmpty(message = MANDATORY)
    @Length(min=NUMBER_MIN_SIZE, max=MAX_SIZE, message="The length must be between 3 and 90 characters")
    private String name;

    public BankDTO(Bank bank) {
        this.id = bank.getId();
        this.name = bank.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
