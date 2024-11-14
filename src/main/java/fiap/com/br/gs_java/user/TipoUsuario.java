package fiap.com.br.gs_java.user;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "T_SOSE_TipoUsuario")
public class TipoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, nullable = false)
    private String descricao;

}

