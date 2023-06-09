package tech.escalab.apicourse.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.escalab.apicourse.model.entity.Course;
import tech.escalab.apicourse.model.entity.Institution;

import java.util.ArrayList;
import java.util.List;


@Repository
public interface CourseRepository extends JpaRepository<Course, Integer>, CourseCriteriaRepository {

}


