package grupo2.com.ecoPoint.Model.Entity;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Certificado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String urlDocumento;
    private LocalDate dataEmissao;
    private String tipo; // CDF, CRT, MTR...

    @OneToOne
    @JoinColumn(name = "solicitacao_id")
    private Solicitacao solicitacao;

    // Construtor vazio obrigatório para JPA
    public Certificado() {}

    // Construtor com parâmetros
    public Certificado(String urlDocumento, LocalDate dataEmissao, String tipo) {
        this.urlDocumento = urlDocumento;
        this.dataEmissao = dataEmissao;
        this.tipo = tipo;
    }


    public Long getId() {
        return id;
    }

    public String getUrlDocumento() {
        return urlDocumento;
    }

    public void setUrlDocumento(String urlDocumento) {
        this.urlDocumento = urlDocumento;
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


