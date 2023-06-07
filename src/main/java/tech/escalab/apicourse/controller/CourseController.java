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
import tech.escalab.apicourse.model.dto.CourseRequest;
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
    public ResponseEntity<List<CourseRequest>> getCourses(){

        List<CourseRequest> courses = courseService.getCourses();

        if (courses.size() > 0){
            return new ResponseEntity<>(courses, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    //Get one course
    @GetMapping("/{id}") //add the params {id}
    public ResponseEntity<Course> getCourse(@PathVariable("id") int id){ //@PathVariable for params
        Course course = courseService.getCourse(id);
        if (course == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(course,HttpStatus.OK);
    }

    //create one course
    @PostMapping("/") //method http post
    public ResponseEntity<Course> insertCourse (@Valid @RequestBody Course courseRequest){ //RequestBody to define structure
        Course course = courseService.insertCourse(courseRequest);
        return new ResponseEntity<>(course, HttpStatus.CREATED);
    }


    //update one course
    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable("id") int id, @RequestBody Course courseRequest){
        Course course = courseService.updateCourse(id, courseRequest);

        if(course == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Course> deleteCourse(@PathVariable("id") int id){
        Course course = courseService.deleteCourse(id);

        if (course == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(course, HttpStatus.OK);
    }
}
