package tech.escalab.apicourse.service;

import tech.escalab.apicourse.model.entity.Course;

import java.util.List;

public interface ICourseService {

    List<Course> getCourses();

    Course getCourse(int id);

    Course insertCourse(Course course);

    Course updateCourse(int id, Course updateCourse);

    Course deleteCourse(int id);
}
