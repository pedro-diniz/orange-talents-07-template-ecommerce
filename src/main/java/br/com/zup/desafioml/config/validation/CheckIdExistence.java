package br.com.zup.desafioml.config.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CheckIdExistenceValidator.class) //
@Target({ElementType.FIELD}) // aplicável em atributos
@Retention(RetentionPolicy.RUNTIME) // pode ser lida em runtime na aplicação
public @interface CheckIdExistence {

    // Mensagem padrão aplicada quando a validação falhar
    String message() default "objeto com esse id não existe";

    // Validação para validation groups. Pouco utilizada.
    Class<?>[] groups() default {};

    // Possibilidade de envio de informações extras. Pouco utilizada.
    Class<? extends Payload>[] payload() default{};

    // Nome do campo que contém o id, normalmente id
    String fieldName();

    // Classe de domínio que contém o ID
    Class<?> domainClass();

}
