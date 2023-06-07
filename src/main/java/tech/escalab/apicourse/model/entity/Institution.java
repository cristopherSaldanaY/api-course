package tech.escalab.apicourse.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "institutions")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Institution {

    /*@Min(value = 1, message = "El id de Institución es obligatorio") */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int institutionId;

    /*@NotEmpty(message = "El nombre de Institucion es obligatorio") */
    @Column(nullable = false, unique = true)
    private String name;


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("InstitutionEntity{");
        sb.append("id=").append(institutionId);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Institution that = (Institution) o;
        return institutionId == that.institutionId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(institutionId);
    }
}
