package br.com.zup.desafioml.config.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ApiExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    // Retorna HTTP 400 e cobre todas as chamadas no MethodArgumentNotValidException, usado no @Valid
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ValidationErrorOutputDto handleValidationException(MethodArgumentNotValidException exception) {

        // Obtém listas dos erros globais e de campo referentes à exceção.
        List<ObjectError> globalErrors = exception.getBindingResult().getGlobalErrors();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        // Constrói o objeto ValidationErrorOutputDto e o retorna na exceção.
        return buildValidationErrors(globalErrors, fieldErrors);
    }

    // Constrói o ValidationErrorOutputDto a partir das listas de erros
    public ValidationErrorOutputDto buildValidationErrors(
            List<ObjectError> globalErrors, List<FieldError> fieldErrors) {

        // Instancia o objeto vazio da classe ValidationErrorOutputDto (VEOD)
        ValidationErrorOutputDto validationErrors = new ValidationErrorOutputDto();

        // Adiciona os erros globais à lista de erros globais do objeto VEOD.
        globalErrors.forEach(error -> validationErrors.addError(getErrorMessage(error)));

        // Adiciona os erros de campo à lista de erros de campo do objeto VEOD
        fieldErrors.forEach(error -> {
            // Obtém os fieldErrors
            String errorMessage = getErrorMessage(error);

            // Cria os objetos da classe FieldErrorOutputDto e os adiciona na lista de erros de campo
            // do VEOD
            validationErrors.addFieldError(error.getField(), errorMessage);
        });
        return validationErrors;
    }

    // Obtém as mensagens de erro a partir da entidade MessageSource
    public String getErrorMessage(ObjectError error) {
        return messageSource.getMessage(error, LocaleContextHolder.getLocale());
    }

}
