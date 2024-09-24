package br.com.vicente.multitoolsbackend.shared.infraestructure.exception;

import br.com.vicente.multitoolsbackend.shared.usecase.exception.UseCaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private MessageSource messageSource;
    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<InfraError> handleNotFoundException(final NotFoundException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(InfraError.from(ex));
    }
    @ExceptionHandler(value = NotFoundSimpleException.class)
    public ResponseEntity<InfraError> handleNotFoundSimpleException(final NotFoundSimpleException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(InfraError.from(ex));
    }

    @ExceptionHandler(value = UseCaseException.class)
    public ResponseEntity<InfraError> handleUseCaseException(final UseCaseException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(InfraError.from(ex));
    }

    @ResponseStatus(code=HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<FormError>> handleMethodArgumentNotValidExceptionMethodArgumentNotValidException(final MethodArgumentNotValidException exception) {
        List<FormError> errorsForm = new ArrayList<>();

        final List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.forEach(fieldError->{
            final String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            final FormError formError = new FormError(fieldError.getField(), message);
            errorsForm.add(formError);
        });
        return ResponseEntity.badRequest().body(errorsForm);
    }
}
