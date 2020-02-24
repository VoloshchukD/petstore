package by.tms.petstore.exception.exController;

import by.tms.petshop.exception.InvalidIDException;
import by.tms.petstore.exception.storeException.InvalidOrderException;
import by.tms.petstore.exception.storeException.OrderNotFoundException;
import by.tms.petstore.model.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class StoreExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ApiResponse> notFound() {
        return new ResponseEntity( new ApiResponse(404,"Error","Order not found"), HttpStatus.NOT_FOUND );
    }

    @ExceptionHandler(InvalidIDException.class)
    public ResponseEntity<ApiResponse> invalidID() {
        return new ResponseEntity( new ApiResponse(400,"Error","Invalid ID supplied"), HttpStatus.BAD_REQUEST );
    }

    @ExceptionHandler(InvalidOrderException.class)
    public ResponseEntity<ApiResponse> invalidOrder() {
        return new ResponseEntity( new ApiResponse(400,"Error","Invalid Order"), HttpStatus.BAD_REQUEST );
    }

}
