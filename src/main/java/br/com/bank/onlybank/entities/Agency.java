package br.com.bank.onlybank.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

import static br.com.bank.onlybank.constants.ValidationConstraints.NAME_IS_REQUIRED;
import static br.com.bank.onlybank.constants.ValidationConstraints.NUMBER_IS_REQUIRED;
import static br.com.bank.onlybank.constants.ValidationConstraints.NUMBER_MAX_SIZE;
import static br.com.bank.onlybank.constants.ValidationConstraints.NUMBER_MAX_SIZE_IS;
import static br.com.bank.onlybank.constants.ValidationConstraints.NUMBER_MIN_SIZE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "AGENCY")
public class Agency implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = NUMBER_IS_REQUIRED)
    @Size(min = NUMBER_MIN_SIZE, max = NUMBER_MAX_SIZE, message = NUMBER_MAX_SIZE_IS)
    private String number;

    @NotBlank(message = NAME_IS_REQUIRED)
    private String name;

    @ManyToOne
    @JoinColumn(name = "banco_id")
    private Bank bank;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((bank == null) ? 0 : bank.hashCode());
        result = prime * result + ((number == null) ? 0 : number.hashCode());
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
        Agency other = (Agency) obj;
        if (bank == null) {
            if (other.bank != null) {
                return false;
            }
        } else if (!bank.equals(other.bank)) {
            return false;
        }
        if (number == null) {
            if (other.number != null) {
                return false;
            }
        } else if (!number.equals(other.number)) {
            return false;
        }
        return true;
    }
}