package tech.escalab.apicourse.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;




@ControllerAdvice //define la clase que proporciona métodos de manejo de excepciones globales para la aplicación.
public class CourseExceptionHandler {

    /*
        indicar que un método en una clase anotada con @ControllerAdvice manejará una excepción específica que
        puede ser lanzada por uno o varios métodos en los controladores de una aplicación
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> badRequestHandler (MethodArgumentNotValidException exception, WebRequest webRequest){
        ErrorHttp errorHttp = new ErrorHttp(
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now(),
                ErrorDetail.errorMap(exception.getFieldErrors()),
                webRequest.getDescription(false)
        );

        return new ResponseEntity<>(errorHttp, HttpStatus.BAD_REQUEST);
    }
}
