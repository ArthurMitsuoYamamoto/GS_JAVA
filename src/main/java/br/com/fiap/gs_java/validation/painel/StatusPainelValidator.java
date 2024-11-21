package br.com.fiap.gs_java.validation.painel;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StatusPainelValidator implements ConstraintValidator<StatusPainel, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.equals("ATIVO") || value.equals("INATIVO");
    }
}
