package fiap.com.br.gs.java.validation.usuario;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TipoUsuarioValidator implements ConstraintValidator<TipoUsuario, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.equals("Administrador") || value.equals("Usuário comum");
    }
}
