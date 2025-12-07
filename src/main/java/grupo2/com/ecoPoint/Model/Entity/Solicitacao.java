package grupo2.com.ecoPoint.Model.Entity;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

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
    @JoinColumn(name = "empresa_geradora_id")   /* Relaciona com a empresa geradora, Varias solicitações para uma empresa */
    private EmpresaGeradora empresaGeradora;

    @ManyToOne
    @JoinColumn(name = "empresa_coletora_id")  /* Relaciona com a empresa coletora, varias solicitações para uma empresa */
    private EmpresaColetora empresaColetora;
    
    @CreationTimestamp                         /* automaticamente vai registrar a data da criação da solicitação */
    private LocalDate dataSolicitacao; 

    private LocalDate dataAgendada;

    @Enumerated(EnumType.STRING) /* Vai ser armazenado no banco como ENUM(Variaveis com itens estabelecidos) */
    private StatusSolicitacao status;

    @ManyToMany
    @JoinTable(                          /* Varios itens para varies solicitações*/
        name = "solicitacao_itens",     /* Sem a criação desta tabela intermediaria para relacionar empresa com item, nao podemos ter o ManyToMany*/
        joinColumns = @JoinColumn(name = "solicitacao_id"), 
        inverseJoinColumns = @JoinColumn(name = "itemResiduo_id")
        /* Relacionando o item-residuo com a solicitação */
    )
    private List<ItemResiduo> itens;

    @OneToOne(mappedBy = "solicitacao", cascade = CascadeType.ALL)  /* o mapped significa que o relacionamento é mapeado pelo campo que tem dentro da entidade Certificado */
    private Certificado certificado;                                /* Caso mexa em uma solicitação o exclua o certificado sera afetado junto, cascade = CascadeType.ALL  */

    public Solicitacao() {                                          /* Construtor vazio obrigatório para JPA */

    } 

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
