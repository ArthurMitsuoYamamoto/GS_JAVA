package fiap.com.br.gs_java.user;

import fiap.com.br.gs_java.user.notification.Notificacao;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "T_SOSE_TipoUsuario")
public class TipoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String descricao;

    // Relacionamento muitos-para-muitos com Usuario
    @ManyToMany(mappedBy = "tipoUsuario")
    private List<Usuario> usuarios;

    // Relacionamento um-para-muitos com Notificacao
    @OneToMany(mappedBy = "tipoUsuario")
    private List<Notificacao> notificacoes;
}