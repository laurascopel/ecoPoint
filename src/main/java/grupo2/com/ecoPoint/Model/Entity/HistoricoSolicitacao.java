package grupo2.com.ecoPoint.Model.Entity;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

@Entity
public class HistoricoSolicitacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "solicitacao_id")
    private Solicitacao solicitacao;

    private LocalDateTime data;

    @Enumerated(EnumType.STRING)
    private StatusSolicitacao statusNovo;

    private String observacao;

    // Construtor vazio obrigatório para JPA
    public HistoricoSolicitacao() {}

    // Construtor com parâmetros
    public HistoricoSolicitacao(LocalDateTime data, StatusSolicitacao statusNovo, String observacao) {
        this.data = data;
        this.statusNovo = statusNovo;
        this.observacao = observacao;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public Solicitacao getSolicitacao() {
        return solicitacao;
    }

    public void setSolicitacao(Solicitacao solicitacao) {
        this.solicitacao = solicitacao;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public StatusSolicitacao getStatusNovo() {
        return statusNovo;
    }

    public void setStatusNovo(StatusSolicitacao statusNovo) {
        this.statusNovo = statusNovo;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
