package fiap.com.br.gs.java.validation.usuario.notificacao;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TipoNotificacaoUsuarioValidator implements ConstraintValidator<TipoNotificacaoUsuario, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.equals("ADM") || value.equals("CLIENTE");
    }
}
