package tech.escalab.apicourse.model.repository;

import org.springframework.stereotype.Component;

import tech.escalab.apicourse.model.entity.Course;
import tech.escalab.apicourse.model.entity.Institution;

import java.util.ArrayList;
import java.util.List;


@Component
public class CourseRepository {

    public static List<Course> courses = new ArrayList<>();

    //for testing
    static {
        courses.add(new Course(1, "Java Fundamentals", new Institution(1, "Escalab")));
        courses.add(new Course(2, "NodeJS con TypeScript", new Institution(1, "Escalab")));
    }

    public static List<Course> getCourses(){
        return courses;
    }

    public static Course getCourse(int id){
        for (Course course : courses) {
            if (course.getId() == id){
                return course;
            }
        }
        return null;
    }

    public static Course insertCourse(Course course){
        courses.add(course);
        return course;
    }

    public static Course updateCourse(int id, Course updateCourse){
        for (Course course : courses) {
            if (course.getId() == id){
                course.setName(updateCourse.getName());
                course.setInstitution(updateCourse.getInstitution());
                return course;
            }
        }

        return null;
    }

    public static Course deleteCourse(int id){
        for (Course course : courses) {
            if(course.getId() == id){
                courses.remove(course);
                return course;
            }
        }

        return null;
    }
}
