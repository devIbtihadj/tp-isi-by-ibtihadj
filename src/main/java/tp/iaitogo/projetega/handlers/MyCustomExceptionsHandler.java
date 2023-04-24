package tp.iaitogo.projetega.handlers;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tp.iaitogo.projetega.exceptions.ClientNotFoundException;
import tp.iaitogo.projetega.exceptions.CompteNotFoundException;
import tp.iaitogo.projetega.exceptions.DateFormatNotCorrectException;
import tp.iaitogo.projetega.exceptions.SoldeInsuffissantException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;


@RestControllerAdvice
public class MyCustomExceptionsHandler {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<String> ClientNotFoundException(ClientNotFoundException exception) {
        LOGGER.error(exception.getMessage());
        return ResponseEntity.status(BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler(CompteNotFoundException.class)
    public ResponseEntity<String> CompteNotFoundException(CompteNotFoundException exception) {
        LOGGER.error(exception.getMessage());
        return ResponseEntity.status(BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler(SoldeInsuffissantException.class)
    public ResponseEntity<String> SoldeInsuffissantException(SoldeInsuffissantException exception) {
        LOGGER.error(exception.getMessage());
        return ResponseEntity.status(BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<String> violationDeContrainteDIntegriteSql(SQLIntegrityConstraintViolationException exception) {
        LOGGER.error(exception.getMessage());
        return ResponseEntity.status(500).body("Impossible de supprimer ce client car il possède un compte");
    }

    @ExceptionHandler(DateFormatNotCorrectException.class)
    public ResponseEntity<String> DateFormatNotCorrectException(DateFormatNotCorrectException exception) {
        LOGGER.error(exception.getMessage());
        return ResponseEntity.status(BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> ConstraintViolationException(ConstraintViolationException exception) {
        LOGGER.error(exception.getMessage());
        List<String> errors = new ArrayList<>();
        exception.getConstraintViolations().forEach(constraintViolation -> {
            errors.add(constraintViolation.getMessageTemplate());
        });
        return ResponseEntity.status(BAD_REQUEST).body(errors);


    }



}
