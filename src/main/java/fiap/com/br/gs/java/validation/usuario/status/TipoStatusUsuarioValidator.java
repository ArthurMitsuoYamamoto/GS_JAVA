package fiap.com.br.gs.java.validation.usuario.status;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TipoStatusUsuarioValidator implements ConstraintValidator<TipoStatusUsuario, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.equals("A") || value.equals("I");
    }
}
