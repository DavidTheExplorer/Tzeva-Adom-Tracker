package dte.tzevaadomtracker.exceptions.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionLogger
{
    private final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionLogger.class);

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handle(DataAccessException exception)
    {
        LOGGER.info("There was a database error", exception);

        return reportNowResponse();
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handle(IllegalArgumentException exception)
    {
        return createErrorResponse(exception.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handle(MethodArgumentNotValidException exception)
    {
        String[] errorMessages = exception.getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .toArray(String[]::new);

        return createErrorResponse(errorMessages);
    }

    private ResponseEntity<ErrorResponse> createErrorResponse(String... errorMessages)
    {
        return ResponseEntity.badRequest()
                .body(new ErrorResponse(errorMessages));
    }

    private ResponseEntity<ErrorResponse> reportNowResponse()
    {
        return ResponseEntity.internalServerError()
                .body(new ErrorResponse("Error while handling the request. Please report this error to an admin!"));
    }
}
