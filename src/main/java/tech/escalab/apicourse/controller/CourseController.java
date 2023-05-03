package tech.escalab.apicourse.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.escalab.apicourse.entity.CourseEntity;
import tech.escalab.apicourse.entity.InstitutionEntity;

import java.util.ArrayList;
import java.util.List;

@RestController //it's class is a controller (entry point)
@RequestMapping("/courses") //directions expose
public class CourseController {

    public static List<CourseEntity> courses = new ArrayList<>();

    //for testing
    static {
        courses.add(new CourseEntity(1, "Java Fundamentals", new InstitutionEntity(1, "Escalab")));
        courses.add(new CourseEntity(2, "NodeJS con TypeScript", new InstitutionEntity(1, "Escalab")));
    }


    //Get all courses
    @GetMapping("/") //method http get
    public ResponseEntity<List<CourseEntity>> getCourses(){

        if (courses.size() > 0){
            return new ResponseEntity<>(courses, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    //Get one course
    @GetMapping("/{id}") //add the params {id}
    public ResponseEntity<CourseEntity> getCourse(@PathVariable("id") int id){ //@PathVariable for params
        for (CourseEntity course: courses) {
           if (course.getId() == id){
               return new ResponseEntity<CourseEntity>(course, HttpStatus.OK);
           }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //create one course
    @PostMapping("/") //method http post
    public ResponseEntity<CourseEntity> insertCourse (@Valid @RequestBody CourseEntity course){ //RequestBody to define structure
        courses.add(course);
        return new ResponseEntity<>(course, HttpStatus.CREATED);
    }


    //update one course
    @PutMapping("/{id}")
    public ResponseEntity<CourseEntity> updateCourse(@PathVariable("id") int id, @RequestBody CourseEntity updateCourse){
        for (CourseEntity course : courses) {
            if (course.getId() == id){
                course.setName(updateCourse.getName());
                course.setInstitution(updateCourse.getInstitution());

                return new ResponseEntity<>(course, HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CourseEntity> deleteCourse(@PathVariable("id") int id){

        for (CourseEntity course : courses) {
            if(course.getId() == id){
                courses.remove(course); // Eliminar el curso de la lista de cursos
                return new ResponseEntity<>(course, HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
