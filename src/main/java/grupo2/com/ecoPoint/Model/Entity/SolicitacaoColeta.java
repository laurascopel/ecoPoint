package grupo2.com.ecoPoint.Model.Entity;

import java.util.List;
import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity

public class SolicitacaoColeta {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;
@ManyToOne
@JoinColumn(name = "empresa_geradora_id")
private EmpresaGeradora empresaGeradora;

@ManyToOne
@JoinColumn(name = "empresa_coletora_id")
private EmpresaColetora empresaColetora;

public SolicitacaoColeta() {
}

private LocalDate dataSolicitacao;

private LocalDate dataAgendada;
  // Status da solicitação
@Enumerated(EnumType.STRING)
private StatusSolicitacao status;

    // Documento enviado pela coletora ao final
private String certificadoUrl;

    // Itens da coleta (lista de resíduos)
@OneToMany(mappedBy = "solicitacaoColeta", cascade = CascadeType.ALL)
private List<ItemResiduo> itens;

    // Getters e Setters
public Integer getId() { return id; }
public void setId(Integer id) { this.id = id; }

public EmpresaGeradora getEmpresaGeradora() { return empresaGeradora; }
public void setEmpresaGeradora(EmpresaGeradora empresaGeradora) { this.empresaGeradora = empresaGeradora; }

public EmpresaColetora getEmpresaColetora() { return empresaColetora; }
public void setEmpresaColetora(EmpresaColetora empresaColetora) { this.empresaColetora = empresaColetora; }

public LocalDate getDataSolicitacao() { return dataSolicitacao; }
public void setDataSolicitacao(LocalDate dataSolicitacao) { this.dataSolicitacao = dataSolicitacao; }

public LocalDate getDataAgendada() { return dataAgendada; }
public void setDataAgendada(LocalDate dataAgendada) { this.dataAgendada = dataAgendada; }

public StatusSolicitacao getStatus() { return status; }
public void setStatus(StatusSolicitacao status) { this.status = status; }

public String getCertificadoUrl() { return certificadoUrl; }
public void setCertificadoUrl(String certificadoUrl) { this.certificadoUrl = certificadoUrl; }

public List<ItemResiduo> getItens() { return itens; }
public void setItens(List<ItemResiduo> itens) {
        this.itens = itens;
        }
    }

