package fiap.com.br.gs.java.validation.usuario.status;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TipoStatusUsuarioValidator.class)
public @interface TipoStatusUsuario {

    String message() default "{usuario.status}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

