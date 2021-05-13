package br.com.bank.onlybank.dtos;

import br.com.bank.onlybank.entities.Client;
import br.com.bank.onlybank.enums.Profile;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

import static br.com.bank.onlybank.constants.ValidationConstraints.CPF_IS_INVALID;
import static br.com.bank.onlybank.constants.ValidationConstraints.MANDATORY;
import static br.com.bank.onlybank.constants.ValidationConstraints.MAX_SIZE;
import static br.com.bank.onlybank.constants.ValidationConstraints.MIN_SIZE;
import static br.com.bank.onlybank.constants.ValidationConstraints.THE_LENGTH_MUST_BE_BETWEEN_5_AND_120_CHARACTERS;

public class ClientDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = MANDATORY)
    private Long id;

    @NotEmpty(message = MANDATORY)
    @Length(min = MIN_SIZE, max = MAX_SIZE, message = THE_LENGTH_MUST_BE_BETWEEN_5_AND_120_CHARACTERS)
    private String name;

    @NotEmpty(message = MANDATORY)
    @CPF(message = CPF_IS_INVALID)
    private String cpf;

    private Set<Profile> profiles;

    public ClientDTO(Client client) {
        this.id = client.getId();
        this.name = client.getName();
        this.cpf = client.getCpf();
        this.profiles = client.getProfile();
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Set<Profile> getProfiles() {
        return profiles;
    }

    public void setProfiles(Set<Profile> profiles) {
        this.profiles = profiles;
    }
}




