//package br.com.vicente.multitoolsbackend.shared.infraestructure.exception;
//
//import br.com.vicente.multitoolsbackend.shared.usecase.exception.UseCaseException;
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//@ControllerAdvice
//public class GlobalExceptionHandler {
//
//    @ExceptionHandler(value = NotFoundException.class)
//    public ResponseEntity<InfraError> handleNotFoundException(final NotFoundException ex) {
//
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(InfraError.from(ex));
//    }
//
//    @ExceptionHandler(value = UseCaseException.class)
//    public ResponseEntity<InfraError> handleUseCaseException(final UseCaseException ex) {
//
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(InfraError.from(ex));
//    }
//}
