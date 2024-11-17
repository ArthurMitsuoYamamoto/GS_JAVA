package fiap.com.br.gs.java.usuario.notificacao;

import fiap.com.br.gs.java.usuario.Usuario;
import fiap.com.br.gs.java.usuario.notificacao.dto.NotificacaoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificacaoService {

    private final NotificacaoRepository notificacaoRepository;
    private final TipoNotificacaoRepository tipoNotificacaoRepository;

    @Transactional
    public NotificacaoResponse criarNotificacao(String mensagem, Long tipoNotificacaoId, Usuario usuario) {
        TipoNotificacao tipoNotificacao = tipoNotificacaoRepository.findById(tipoNotificacaoId)
                .orElseThrow(() -> new IllegalArgumentException("Tipo de Notificação não encontrado"));

        Notificacao notificacao = Notificacao.builder()
                .mensagem(mensagem)
                .dataEnvio(new java.util.Date())
                .tipoNotificacao(tipoNotificacao)
                .usuario(usuario)
                .build();

        notificacaoRepository.save(notificacao);

        return NotificacaoResponse.from(notificacao);
    }

    @Transactional(readOnly = true)
    public List<NotificacaoResponse> listarNotificacoesPorUsuario(Long usuarioId) {
        return notificacaoRepository.findAll().stream()
                .filter(notificacao -> notificacao.getUsuario().getId().equals(usuarioId))
                .map(NotificacaoResponse::from)
                .collect(Collectors.toList());
    }
}
