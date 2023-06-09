package tech.escalab.apicourse.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.escalab.apicourse.model.dto.CourseRequest;
import tech.escalab.apicourse.model.entity.Course;

import tech.escalab.apicourse.service.impl.CourseServiceImpl;

import java.util.List;


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
    public ResponseEntity<CourseRequest> getCourse(@PathVariable("id") int id){ //@PathVariable for params
        CourseRequest courseRequest = courseService.getCourse(id);
        if (courseRequest == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(courseRequest,HttpStatus.OK);
    }

    //create one course
    @PostMapping("/") //method http post
    public ResponseEntity<CourseRequest> insertCourse (@Valid @RequestBody CourseRequest courseRequest){ //RequestBody to define structure
        CourseRequest course = courseService.insertCourse(courseRequest);
        return new ResponseEntity<>(course, HttpStatus.CREATED);
    }


    //update one course
    @PutMapping("/{id}")
    public ResponseEntity<CourseRequest> updateCourse(@PathVariable("id") int id, @RequestBody Course courseRequest){
        CourseRequest course = courseService.updateCourse(id, courseRequest);

        if(course == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CourseRequest> deleteCourse(@PathVariable("id") int id){
        CourseRequest course = courseService.deleteCourse(id);

        if (course == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @GetMapping(path = "/", params = {"name"})
    public ResponseEntity<CourseRequest> getCourseByName(@RequestParam String name){
        CourseRequest courseRequest = courseService.getCourseByName(name);

        if (courseRequest == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(courseRequest, HttpStatus.OK);
    }

    @GetMapping(path = "/institution", params = {"name"})
    public ResponseEntity<CourseRequest> getCourseByInstitution(@RequestParam String name){

        CourseRequest courseRequest = courseService.getCourseByInstitution(name);

        if (courseRequest == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(courseRequest, HttpStatus.OK);
    }
}

