package fiap.com.br.gs_java.user;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Cacheable
@NoArgsConstructor
@Table(name = "T_SOSE_TipoUsuario")
public class TipoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String descricao;

    @ManyToMany(mappedBy = "tiposUsuario")
    private List<Usuario> usuarios;
}

