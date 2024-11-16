package fiap.com.br.gs.java.usuario;

import fiap.com.br.gs.java.painel.PainelSolar;
import fiap.com.br.gs.java.usuario.tipo.TipoUsuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

@Entity
@Data
@Builder
@Cacheable
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_SOSE_Usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 100)
    @Email
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(name = "DataCadastro")
    private LocalDateTime dataCadastro;

    @Column(nullable = false)
    private char status;

    @OneToMany(mappedBy = "usuario")
    private List<PainelSolar> paineis = new ArrayList<>();  // Inicializado para evitar NullPointerException

    @ManyToMany
    @JoinTable(
            name = "T_SOSE_Usuario_TipoUsuario",
            joinColumns = @JoinColumn(name = "idUsuario"),
            inverseJoinColumns = @JoinColumn(name = "idTipoUsuario")
    )
    private List<TipoUsuario> tipoUsuario = new ArrayList<>();  // Inicializado para evitar NullPointerException

}
