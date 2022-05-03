package cinema.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {ApiException.class})
    public ResponseEntity<Object> handleApiException(ApiException e) {
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto(e.getMessage());

        return new ResponseEntity<>(exceptionResponseDto, e.getStatus());
    }
}
