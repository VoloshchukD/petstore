package by.tms.petstore.exception.exController;


import by.tms.petstore.exception.petException.*;
import by.tms.petstore.model.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class PetExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PetNotFoundException.class)
    public ResponseEntity<ApiResponse> notFound() {
        return new ResponseEntity( new ApiResponse(404,"Error","Pet not found"), HttpStatus.NOT_FOUND );
    }

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<ApiResponse> invalidInput() {
        return new ResponseEntity( new ApiResponse(405,"Error","Invalid input"), HttpStatus.METHOD_NOT_ALLOWED );
    }

    @ExceptionHandler(by.tms.petshop.exception.InvalidIDException.class)
    public ResponseEntity<ApiResponse> invalidID() {
        return new ResponseEntity( new ApiResponse(400,"Error","Invalid ID supplied"), HttpStatus.BAD_REQUEST );
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ApiResponse> valid() {
        return new ResponseEntity( new ApiResponse(405,"Error"," Validation exception"), HttpStatus.METHOD_NOT_ALLOWED );
    }

    @ExceptionHandler(InvalidStatusException.class)
    public ResponseEntity<ApiResponse> invalidStatus() {
        return new ResponseEntity( new ApiResponse(400,"Error"," Invalid status value"), HttpStatus.BAD_REQUEST );
    }

}
