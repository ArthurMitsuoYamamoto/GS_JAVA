package fiap.com.br.gs.java.usuario.notificacao.dto;

import fiap.com.br.gs.java.usuario.dto.UsuarioResponse;
import fiap.com.br.gs.java.usuario.notificacao.Notificacao;
import fiap.com.br.gs.java.usuario.notificacao.TipoNotificacao;

import java.util.Date;

public record NotificacaoResponse(
        Long id,
        String mensagem,
        Date dataEnvio,
        TipoNotificacao tipoNotificacao,
        UsuarioResponse usuario
) {
    public static NotificacaoResponse from(Notificacao notificacao) {
        return new NotificacaoResponse(
                notificacao.getId(),
                notificacao.getMensagem(),
                notificacao.getDataEnvio(),
                notificacao.getTipoNotificacao(),
                UsuarioResponse.from(notificacao.getUsuario())
        );
    }
}
