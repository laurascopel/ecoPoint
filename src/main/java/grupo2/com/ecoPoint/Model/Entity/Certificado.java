package grupo2.com.ecoPoint.Model.Entity;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;


@Entity
public class Certificado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) /* Definir ID como chave primaria, é um numero sequencial  */ 
    private Long id;
    private LocalDate dataEmissao;
    private String tipo;                                /* MTR...ETC */
    @Lob
    private byte[] documento;                            /* Para anexos grandes lob e byte */

    @OneToOne                                  /* Relação de um para um entre certificado e solicitação */
    @JoinColumn(name = "solicitacao_id")         /*foreign key - chave unica de outra entidade(chave estrangeira) está relacionando uma solicitação para varios certificados */
    private Solicitacao solicitacao;


    public Certificado() {}                           /* Construtor vazio obrigatório para JPA */

  
    public Certificado(byte[] documento, LocalDate dataEmissao, String tipo) {             /* Construtor */
        this.documento = documento;
        this.dataEmissao = dataEmissao;
        this.tipo = tipo;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getDocumento() {
        return documento;
    }

    public void setDocumento(byte[] documento) {
        this.documento = documento;
    }

    public LocalDate getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(LocalDate dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Solicitacao getSolicitacao() {
        return solicitacao;
    }

    public void setSolicitacao(Solicitacao solicitacao) {
        this.solicitacao = solicitacao;
    }
}


