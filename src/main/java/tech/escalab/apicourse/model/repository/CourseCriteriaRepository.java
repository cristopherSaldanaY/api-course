package tech.escalab.apicourse.model.repository;

import tech.escalab.apicourse.model.entity.Course;

import java.util.List;

public interface CourseCriteriaRepository {
    List<Course> getAllCourses();
    Course getCourseByName(String name);
    Course getCourseByInstitution(String institution);
}
