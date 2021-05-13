package br.com.bank.onlybank.dtos;

import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotEmpty;

import static br.com.bank.onlybank.constants.ValidationConstraints.CPF_IS_INVALID;
import static br.com.bank.onlybank.constants.ValidationConstraints.MANDATORY;

public class NewClientDTO {

    @NotEmpty(message = MANDATORY)
    private String name;

    @NotEmpty(message = MANDATORY)
    @CPF(message = CPF_IS_INVALID)
    private String cpf;

    @NotEmpty(message = MANDATORY)
    private String key;

    public NewClientDTO(String name, String cpf, String key) {
        super();
        this.name = name;
        this.cpf = cpf;
        this.key = key;
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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}