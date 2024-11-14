package fiap.com.br.gs_java.user;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Cacheable
@NoArgsConstructor
@Table(name = "T_SOSE_Usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(name = "DataCadastro")
    private Date dataCadastro;

    @Column(nullable = false)
    private char status;

    @OneToMany(mappedBy = "usuario")
    private List<PainelSolar> paineis;

    @ManyToMany
    @JoinTable(
            name = "T_SOSE_Usuario_TipoUsuario",
            joinColumns = @JoinColumn(name = "idUsuario"),
            inverseJoinColumns = @JoinColumn(name = "idTipoUsuario")
    )
    private List<TipoUsuario> UsersType;
}

