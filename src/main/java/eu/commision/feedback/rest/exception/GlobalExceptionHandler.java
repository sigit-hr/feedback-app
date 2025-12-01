package eu.commision.feedback.rest.exception;

import jakarta.validation.ConstraintViolationException;
import java.net.URI;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final URI VALIDATION_ERROR = URI.create("https://api.local/problem/validation-error");
    private static final URI INVALID_PARAMETER = URI.create("https://api.local/problem/invalid-parameter");
    private static final URI CONSTRAINT_VIOLATION = URI.create("https://api.local/problem/constraint-violation");
    private static final URI INTERNAL_ERROR = URI.create("https://api.local/problem/internal-error");

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidationErrors(MethodArgumentNotValidException ex) {
        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problem.setType(VALIDATION_ERROR);
        problem.setTitle("Validation failed");
        problem.setDetail("One or more fields are invalid.");

        Map<String, String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        field -> field.getField(),
                        field -> field.getDefaultMessage(),
                        (first, second) -> first
                ));

        problem.setProperty("errors", errors);
        return problem;
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ProblemDetail handleEnumErrors(MethodArgumentTypeMismatchException ex) {
        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problem.setType(INVALID_PARAMETER);
        problem.setTitle("Invalid request parameter");
        problem.setDetail("Invalid value for parameter '" + ex.getName() + "'.");

        return problem;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ProblemDetail handleConstraintViolations(ConstraintViolationException ex) {
        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problem.setType(CONSTRAINT_VIOLATION);
        problem.setTitle("Constraint violation");
        problem.setDetail("One or more constraints were violated.");

        Map<String, String> errors = ex.getConstraintViolations()
                .stream()
                .collect(Collectors.toMap(
                        v -> v.getPropertyPath().toString(),
                        v -> v.getMessage()
                ));

        problem.setProperty("errors", errors);
        return problem;
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleGeneric(Exception ex) {
        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        problem.setType(INTERNAL_ERROR);
        problem.setTitle("Internal server error");
        problem.setDetail("An unexpected error occurred.");
        return problem;
    }
}
