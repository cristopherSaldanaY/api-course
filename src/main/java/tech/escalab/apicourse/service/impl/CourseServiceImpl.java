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
import java.util.Optional;

@Service
public class CourseServiceImpl implements ICourseService {

    CourseRepository courseRepository;
    public CourseServiceImpl(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }

    @Override
    public List<CourseRequest> getCourses() {
        /*List<Course> courses = courseRepository.findAll(); */
        List<Course> courses = courseRepository.getAllCourses();
        List<CourseRequest> coursesDto = new ArrayList<>();

        for( Course course : courses){
            coursesDto.add(CourseRequest.mapToDto(course));
        }

        return coursesDto;
    }

    @Override
    public CourseRequest getCourse(int id) {
        Optional<Course> courseOpt = courseRepository.findById(id);

        if(!courseOpt.isEmpty()){
            return new CourseRequest(courseOpt.get().getName(), courseOpt.get().getInstitution());
        }
        return null;
    }


    @Override
    public CourseRequest insertCourse(CourseRequest courseRequest) {
        Course course = CourseRequest.mapToEntity(courseRequest);
        courseRepository.save(course);

        return courseRequest;
    }


    @Override
    public CourseRequest updateCourse(int id, Course updateCourse) {
        Optional<Course> optionalCourse = courseRepository.findById(id);

        if(optionalCourse.isPresent()){
            Course course = optionalCourse.get();
            course.setName(updateCourse.getName());
            course.setInstitution(updateCourse.getInstitution());

            Course courseSave = courseRepository.save(course);

            return CourseRequest.mapToDto(courseSave);

        }
        /*
        Course course = courseRepository.updateCourse(id, updateCourse);
        return course;

         */
        return null;
    }

    @Override
    public CourseRequest deleteCourse(int id) {
        Optional<Course> optionalCourse = courseRepository.findById(id);

        if(optionalCourse.isPresent()){
            Course course = optionalCourse.get();
            courseRepository.deleteById(id);
            CourseRequest courseRequest = CourseRequest.mapToDto(course);

            return courseRequest;
        }

        return null;
    }

    @Override
    public CourseRequest getCourseByName(String name) {
        Course course = courseRepository.getCourseByName(name);

        if (course != null){
            CourseRequest courseRequest = CourseRequest.mapToDto(course);
            return courseRequest;
        }

        return null;
    }

    @Override
    public CourseRequest getCourseByInstitution(String name) {
        Course course = courseRepository.getCourseByInstitution(name);

        if (course != null){
            CourseRequest courseRequest = CourseRequest.mapToDto(course);

            return courseRequest;
        }
        return null;
    }
}
