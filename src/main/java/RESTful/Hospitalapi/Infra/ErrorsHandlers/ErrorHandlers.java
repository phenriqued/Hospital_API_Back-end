package RESTful.Hospitalapi.Infra.ErrorsHandlers;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestControllerAdvice
public class ErrorHandlers {


    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handler404error(EntityNotFoundException exception){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handler400error(MethodArgumentNotValidException exception){
        var errors = exception.getFieldErrors();
        return ResponseEntity.badRequest().body(errors.stream().map(DataErrorValidationDTO::new).toList());
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity handlerSQLDuplicateKey(SQLIntegrityConstraintViolationException exception){
        Pattern pattern = Pattern.compile("'.*?'");
        Matcher matcher = pattern.matcher(exception.getMessage());
        if (matcher.find()){
            return ResponseEntity.badRequest().body("Duplicate Key. \nCheck the key:" + matcher.group(0) +".");
        }else{
            return ResponseEntity.badRequest().build();
        }
    }


    private record DataErrorValidationDTO(String fieldError, String defaultMessage){
        public DataErrorValidationDTO(FieldError fieldError){
            this(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }

}
