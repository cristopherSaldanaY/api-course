package tech.escalab.apicourse.service.impl;

import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tech.escalab.apicourse.controller.CourseController;
import tech.escalab.apicourse.model.entity.Course;
import tech.escalab.apicourse.model.repository.CourseRepository;
import tech.escalab.apicourse.service.ICourseService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

@Service
public class CourseServiceImpl implements ICourseService {

    CourseRepository courseRepository;
    public CourseServiceImpl(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> getCourses() {
        List<Course> courses = courseRepository.getCourses();
        for (Course course : courses){
            course.add(linkTo(methodOn(CourseController.class).getCourse(course.getId())).withSelfRel());
            course.add(linkTo(methodOn(CourseController.class).insertCourse(course)).withRel("createCourse"));
        }
        return courses;
    }

    @Override
    public Course getCourse(int id) {
        List<Course> courses = courseRepository.getCourses();

        for (Course course: courses) {
            if (course.getId() == id){
                return course;
            }
        }
        return null;
    }

    @Override
    public Course insertCourse(Course course) {
        List<Course> courses = courseRepository.getCourses();

        course.add(linkTo(methodOn(CourseController.class).getCourse(course.getId())).withSelfRel());
        course.add(linkTo(methodOn(CourseController.class).getCourses()).withRel(IanaLinkRelations.COLLECTION));

        courses.add(course);
        return course;
    }

    @Override
    public Course updateCourse(int id, Course updateCourse) {
        List<Course> courses = courseRepository.getCourses();
        for (Course course : courses) {
            if (course.getId() == id){
                course.setName(updateCourse.getName());
                course.setInstitution(updateCourse.getInstitution());
                return course;
            }
        }
        return null;
    }

    @Override
    public Course deleteCourse(int id) {
        List<Course> courses = courseRepository.getCourses();
        for (Course course : courses) {
            if(course.getId() == id){
                courses.remove(course); // delete course
                return course;
            }
        }
        return null;
    }
}
