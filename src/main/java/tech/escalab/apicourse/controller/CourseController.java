package tech.escalab.apicourse.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.escalab.apicourse.model.entity.Course;
import tech.escalab.apicourse.model.entity.Institution;
import tech.escalab.apicourse.service.impl.CourseServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController //it's class is a controller (entry point)
@RequestMapping("/courses") //directions expose
public class CourseController {

    CourseServiceImpl courseService;
    public CourseController(CourseServiceImpl courseService){
        this.courseService = courseService;
    }


    //Get all courses
    @GetMapping("/") //method http get
    @Operation(summary = "get all courses")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content =  {
                   @Content(schema = @Schema(implementation = Course.class), mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "204", description = "NO CONTENT", content =  {
                    @Content(schema = @Schema(implementation = Course.class), mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR", content =  {
                    @Content(schema = @Schema())
            })
    })
    public ResponseEntity<List<Course>> getCourses(){

        List<Course> courses = courseService.getCourses();

        if (courses.size() > 0){
            return new ResponseEntity<>(courses, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    //Get one course
    @GetMapping("/{id}") //add the params {id}
    public ResponseEntity<Course> getCourse(@PathVariable("id") int id){ //@PathVariable for params
        List<Course> courses = courseService.getCourses();

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
        List<Course> courses = courseService.getCourses();

        courses.add(course);
        return new ResponseEntity<>(course, HttpStatus.CREATED);
    }


    //update one course
    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable("id") int id, @RequestBody Course updateCourse){
        List<Course> courses = courseService.getCourses();
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
        List<Course> courses = courseService.getCourses();
        for (Course course : courses) {
            if(course.getId() == id){
                courses.remove(course); // delete course
                return new ResponseEntity<>(course, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
