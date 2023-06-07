package tech.escalab.apicourse.service.impl;

import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tech.escalab.apicourse.controller.CourseController;
import tech.escalab.apicourse.model.dto.CourseRequest;
import tech.escalab.apicourse.model.entity.Course;
import tech.escalab.apicourse.model.repository.CourseRepository;
import tech.escalab.apicourse.service.ICourseService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements ICourseService {

    CourseRepository courseRepository;
    public CourseServiceImpl(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }

    @Override
    public List<CourseRequest> getCourses() {
        List<Course> courses = courseRepository.findAll();
        List<CourseRequest> coursesDto = new ArrayList<>();

        for( Course course : courses){
            coursesDto.add(CourseRequest.mapToDto(course));
        }

        return coursesDto;
    }

    @Override
    public Course getCourse(int id) {
        /*Course course = courseRepository.getCourse(id);
        return course;

         */
        return null;
    }

    @Override
    public Course insertCourse(Course course) {
        /*
        courseRepository.insertCourse(course);
        course.add(linkTo(methodOn(CourseController.class).getCourse(course.getId())).withSelfRel());
        course.add(linkTo(methodOn(CourseController.class).getCourses()).withRel(IanaLinkRelations.COLLECTION));

        return course;

         */
        return null;
    }

    @Override
    public Course updateCourse(int id, Course updateCourse) {
        /*
        Course course = courseRepository.updateCourse(id, updateCourse);
        return course;

         */
        return null;
    }

    @Override
    public Course deleteCourse(int id) {
        /*
        Course course = courseRepository.deleteCourse(id);
        return course;

         */
        return null;
    }
}
