package tech.escalab.apicourse.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.escalab.apicourse.entity.Course;
import tech.escalab.apicourse.entity.Institution;

import java.util.ArrayList;
import java.util.List;

@RestController //it's class is a controller (entry point)
@RequestMapping("/courses") //directions expose
public class CourseController {

    public static List<Course> courses = new ArrayList<>();

    //for testing
    static {
        courses.add(new Course(1, "Java Fundamentals", new Institution(1, "Escalab")));
        courses.add(new Course(2, "NodeJS con TypeScript", new Institution(1, "Escalab")));
    }


    //Get all courses
    @GetMapping("/") //method http get
    public ResponseEntity<List<Course>> getCourses(){
        if (courses.size() > 0){
            return new ResponseEntity<>(courses, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    //Get one course
    @GetMapping("/{id}") //add the params {id}
    public ResponseEntity<Course> getCourse(@PathVariable("id") int id){ //@PathVariable for params
        for (Course course: courses) {
           if (course.getId() == id){
               return new ResponseEntity<Course>(course, HttpStatus.OK);
           }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //create one course
    @PostMapping("/") //method http post
    public ResponseEntity<Course> insertCourse (@Valid @RequestBody Course course){ //RequestBody to define structure
        courses.add(course);
        return new ResponseEntity<>(course, HttpStatus.CREATED);
    }


    //update one course
    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable("id") int id, @RequestBody Course updateCourse){
        for (Course course : courses) {
            if (course.getId() == id){
                course.setName(updateCourse.getName());
                course.setInstitution(updateCourse.getInstitution());
                return new ResponseEntity<>(course, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Course> deleteCourse(@PathVariable("id") int id){
        for (Course course : courses) {
            if(course.getId() == id){
                courses.remove(course); // delete course
                return new ResponseEntity<>(course, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
