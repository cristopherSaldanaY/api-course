package tech.escalab.apicourse.entity;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

import java.util.Objects;

public class CourseEntity  {

    @Min(value = 1, message = "El id del curso es obligatorio")
    private int id;
    @NotEmpty(message = "El nombre del curso no puede estar vacío")
    private String name;

    @Valid //valida por que es una clase
    private InstitutionEntity institution;

    public CourseEntity(){}

    public CourseEntity(int id, String name, InstitutionEntity institution){
        this.id = id;
        this.name = name;
        this.institution = institution;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public InstitutionEntity getInstitution() {
        return institution;
    }

    public void setInstitution(InstitutionEntity institution) {
        this.institution = institution;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CourseEntity{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", institution='").append(institution).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseEntity that = (CourseEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
