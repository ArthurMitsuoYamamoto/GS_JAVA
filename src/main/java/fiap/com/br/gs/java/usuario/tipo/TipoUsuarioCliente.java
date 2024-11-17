package fiap.com.br.gs.java.usuario.tipo;

import fiap.com.br.gs.java.usuario.Usuario;
import fiap.com.br.gs.java.usuario.notificacao.Notificacao;
import fiap.com.br.gs.java.validation.usuario.TipoUsuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@Cacheable
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_SOSE_TipoUsuario")
public class TipoUsuarioCliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    @NotBlank(message = "{tipoUsuario.tipoUsuario.notBlank}")
    @TipoUsuario
    String tipoUsuario;

    @Column(nullable = false, length = 50)
    private String descricao;

    // Relacionamento muitos-para-muitos com Usuario
    @ManyToMany(mappedBy = "tipoUsuario")
    private List<Usuario> usuarios;

    // Relacionamento um-para-muitos com Notificacao
    @OneToMany(mappedBy = "tipoUsuario")
    private List<Notificacao> notificacoes;
}