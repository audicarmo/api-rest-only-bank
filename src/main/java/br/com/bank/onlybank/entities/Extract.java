package br.com.bank.onlybank.entities;

import br.com.bank.onlybank.enums.OperationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Calendar;

import static br.com.bank.onlybank.constants.ValidationConstraints.DESCRIPTION_IS_REQUIRED;
import static br.com.bank.onlybank.constants.ValidationConstraints.DESCRIPTION_MAX_SIZE;
import static br.com.bank.onlybank.constants.ValidationConstraints.DESCRIPTION_MIN_SIZE;
import static br.com.bank.onlybank.constants.ValidationConstraints.DESCRIPTION_SIZE_MUST_BE_BETWEEN;
import static br.com.bank.onlybank.constants.ValidationConstraints.VALUE_REQUIRED;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "EXTRACT")
public class Extract implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar date;

    @Enumerated(EnumType.STRING)
    private OperationType operationType;

    @NotNull(message = VALUE_REQUIRED)
    @Column(name = "VALUE")
    private Double value;

    @NotBlank(message = DESCRIPTION_IS_REQUIRED)
    @Size(min = DESCRIPTION_MIN_SIZE, max = DESCRIPTION_MAX_SIZE, message = DESCRIPTION_SIZE_MUST_BE_BETWEEN)
    @Column(name = "INFORMATION")
    private String information;

    @ManyToOne
    private Account account;

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
        Extract other = (Extract) obj;
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
