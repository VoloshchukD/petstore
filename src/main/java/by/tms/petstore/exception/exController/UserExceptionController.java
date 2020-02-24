package by.tms.petstore.exception.exController;

import by.tms.petstore.exception.userException.InvalidPasswordException;
import by.tms.petstore.exception.userException.InvalidUsernameException;
import by.tms.petstore.exception.userException.UserNotFoundException;
import by.tms.petstore.model.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class UserExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponse> notFound() {
        return new ResponseEntity( new ApiResponse(404,"Error","User not found"), HttpStatus.NOT_FOUND );
    }

    @ExceptionHandler(InvalidUsernameException.class)
    public ResponseEntity<ApiResponse> invalidUsername() {
        return new ResponseEntity( new ApiResponse(400,"Error","Invalid username supplied"), HttpStatus.BAD_REQUEST );
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<ApiResponse> invalidPassword() {
        return new ResponseEntity( new ApiResponse(400,"Error","Invalid password supplied"), HttpStatus.BAD_REQUEST );
    }


}
