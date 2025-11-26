package grupo2.com.ecoPoint.Model.Entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Solicitacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "empresa_geradora_id")
    private EmpresaGeradora empresaGeradora;

    @ManyToOne
    @JoinColumn(name = "empresa_coletora_id")
    private EmpresaColetora empresaColetora;

    private LocalDate dataSolicitacao = LocalDate.now();
    private LocalDate dataAgendada;

    @Enumerated(EnumType.STRING)
    private StatusSolicitacao status;

    @ManyToMany
    @JoinTable(
        name = "solicitacao_itens",
        joinColumns = @JoinColumn(name = "solicitacao_id"),
        inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<ItemResiduo> itens;

    @OneToOne(mappedBy = "solicitacao", cascade = CascadeType.ALL)
    private Certificado certificado;

    public Solicitacao() {}

    // Getters e Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public EmpresaGeradora getEmpresaGeradora() {
        return empresaGeradora;
    }
    public void setEmpresaGeradora(EmpresaGeradora empresaGeradora) {
        this.empresaGeradora = empresaGeradora;
    }

    public EmpresaColetora getEmpresaColetora() {
        return empresaColetora;
    }
    public void setEmpresaColetora(EmpresaColetora empresaColetora) {
        this.empresaColetora = empresaColetora;
    }

    public LocalDate getDataSolicitacao() {
        return dataSolicitacao;
    }
    public void setDataSolicitacao(LocalDate dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public LocalDate getDataAgendada() {
        return dataAgendada;
    }
    public void setDataAgendada(LocalDate dataAgendada) {
        this.dataAgendada = dataAgendada;
    }

    public StatusSolicitacao getStatus() {
        return status;
    }
    public void setStatus(StatusSolicitacao status) {
        this.status = status;
    }

    public List<ItemResiduo> getItens() {
        return itens;
    }
    public void setItens(List<ItemResiduo> itens) {
        this.itens = itens;
    }

    public Certificado getCertificado() {
        return certificado;
    }
    public void setCertificado(Certificado certificado) {
        this.certificado = certificado;
    }
}
