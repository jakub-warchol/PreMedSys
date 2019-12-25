package pl.pwsztar.premedsys.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.pwsztar.premedsys.exception.UnrecognizedSymptomesException;

@ControllerAdvice
@Slf4j
class ErrorAdvice {

  @ExceptionHandler(UnrecognizedSymptomesException.class)
  public ResponseEntity<ErrorResponse> handleException(UnrecognizedSymptomesException ex) {
    log.error("Error: " + ex.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
      .body(new ErrorResponse(HttpStatus.NOT_FOUND, UnrecognizedSymptomesException.class.getName(), ex));
  }
}
