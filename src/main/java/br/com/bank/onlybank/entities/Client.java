package br.com.bank.onlybank.entities;

import br.com.bank.onlybank.dtos.NewClientDTO;
import br.com.bank.onlybank.enums.Profile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.bank.onlybank.constants.ValidationConstraints.CPF_IS_REQUIRED;
import static br.com.bank.onlybank.constants.ValidationConstraints.CPF_MAX_SIZE_IS;
import static br.com.bank.onlybank.constants.ValidationConstraints.CPF_SIZE;
import static br.com.bank.onlybank.constants.ValidationConstraints.INVALID_CPF;
import static br.com.bank.onlybank.constants.ValidationConstraints.INVALID_SPECIAL_CHARACTERS_CPF;
import static br.com.bank.onlybank.constants.ValidationConstraints.NAME_IS_REQUIRED;
import static br.com.bank.onlybank.constants.ValidationConstraints.NAME_MAX_SIZE;
import static br.com.bank.onlybank.constants.ValidationConstraints.NAME_MIN_SIZE;
import static br.com.bank.onlybank.constants.ValidationConstraints.NAME_SIZE_MUST_BE_BETWEEN;

@Data
@AllArgsConstructor
@Builder
@Entity(name = "CLIENT")
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = NAME_IS_REQUIRED)
    @Size(min = NAME_MIN_SIZE, max = NAME_MAX_SIZE, message = NAME_SIZE_MUST_BE_BETWEEN)
    private String name;

    @NotBlank(message = CPF_IS_REQUIRED)
    @Size(min = CPF_SIZE, max = CPF_SIZE, message = CPF_MAX_SIZE_IS)
    @CPF(message = INVALID_CPF)
    @Digits(fraction = 0, integer = CPF_SIZE, message = INVALID_SPECIAL_CHARACTERS_CPF)
    @Column(unique = true)
    private String cpf;

    @JsonIgnore
    private String key;

    @ElementCollection(fetch= FetchType.EAGER)
    @CollectionTable(name="PROFILES")
    private Set<Integer> profiles = new HashSet<>();

    public Client() {
        addProfile(Profile.CLIENT);
    }

    public Client(String name, String cpf, String key) {
        super();
        this.name = name;
        this.cpf = cpf;
        this.key = key;
        addProfile(Profile.CLIENT);
    }

    public Client(Long id, String name, String cpf, String key) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.key = key;
    }

    public Client(NewClientDTO newClientDTO) {
        super();
        this.name = newClientDTO.getName();
        this.cpf = newClientDTO.getCpf();
        this.key = newClientDTO.getKey();
        addProfile(Profile.CLIENT);
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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Set<Profile>  getProfile(){
        return this.profiles.stream().map(p -> Profile.toEnum(p)).collect(Collectors.toSet());
    }

    public void addProfile(Profile profile) {
        this.profiles.add(profile.getCode());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Client other = (Client) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }
}
