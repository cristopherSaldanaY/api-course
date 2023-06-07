package tech.escalab.apicourse.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;


import java.util.Objects;

@Entity
@Table(name = "courses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Course extends RepresentationModel<Course> {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int CourseId;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "institution_id", nullable = false)
    private Institution institution;


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CourseEntity{");
        sb.append("id=").append(CourseId);
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
        return CourseId == that.CourseId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(CourseId);
    }
}
