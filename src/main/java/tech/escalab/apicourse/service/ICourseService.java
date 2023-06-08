package tech.escalab.apicourse.service;

import tech.escalab.apicourse.model.dto.CourseRequest;
import tech.escalab.apicourse.model.entity.Course;

import java.util.List;

public interface ICourseService {

    List<CourseRequest> getCourses();

    CourseRequest getCourse(int id);

    CourseRequest insertCourse(CourseRequest courseRequest);

    Course updateCourse(int id, Course course);

    Course deleteCourse(int id);
}
