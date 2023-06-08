package tech.escalab.apicourse.model.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.escalab.apicourse.model.entity.Course;
import tech.escalab.apicourse.model.entity.Institution;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseRequest {

    @NotEmpty(message = "El nombre del curso no puede estar vacío")
    private String name;

    @Valid
    private Institution institution;

    public static CourseRequest mapToDto(Course course){
        CourseRequest courseRequest = new CourseRequest();
        courseRequest.setName(course.getName());
        courseRequest.setInstitution(course.getInstitution());

        return courseRequest;
    }

    public static Course mapToEntity(CourseRequest courseRequest){
        Course course = new Course();
        course.setName(courseRequest.getName());
        course.setInstitution(courseRequest.getInstitution());

        return course;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CourseRequest{");
        sb.append("name='").append(name).append('\'');
        sb.append(", institution=").append(institution);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseRequest that = (CourseRequest) o;
        return Objects.equals(name, that.name) && Objects.equals(institution, that.institution);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, institution);
    }
}
