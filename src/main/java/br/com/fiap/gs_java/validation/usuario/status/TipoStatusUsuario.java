package br.com.fiap.gs_java.validation.usuario.status;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TipoStatusUsuarioValidator.class)
public @interface TipoStatusUsuario {

    String message() default "{usuario.status}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

