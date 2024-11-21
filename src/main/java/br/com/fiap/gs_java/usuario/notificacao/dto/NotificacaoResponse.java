package br.com.fiap.gs_java.usuario.notificacao.dto;

import br.com.fiap.gs_java.usuario.dto.UsuarioResponse;
import br.com.fiap.gs_java.usuario.notificacao.Notificacao;
import br.com.fiap.gs_java.usuario.notificacao.TipoNotificacao;

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
