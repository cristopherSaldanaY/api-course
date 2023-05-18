package tech.escalab.apicourse.model.entity;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.hateoas.RepresentationModel;


import java.util.Objects;

public class Course extends RepresentationModel<Course> {

    @Min(value = 1, message = "El id del curso es obligatorio")
    private int id;
    @NotEmpty(message = "El nombre del curso no puede estar vacío")
    private String name;

    @Valid
    private Institution institution;

    public Course(){}

    public Course(int id, String name, Institution institution){
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

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
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
        Course that = (Course) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
