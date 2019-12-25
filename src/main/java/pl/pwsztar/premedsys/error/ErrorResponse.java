package pl.pwsztar.premedsys.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
class ErrorResponse {

  HttpStatus status;
  String errorCode;
  String message;
  String errorClassName;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
  LocalDateTime date;

  ErrorResponse(HttpStatus status, String errorClassName, Throwable t) {
    this.status = status;
    this.errorClassName = errorClassName;
    this.message = t.getMessage();
    this.errorCode = String.valueOf(status.value());
    this.date = LocalDateTime.now();
  }
}

