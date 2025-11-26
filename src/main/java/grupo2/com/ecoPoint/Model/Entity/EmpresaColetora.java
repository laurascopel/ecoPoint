package grupo2.com.ecoPoint.Model.Entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class EmpresaColetora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String endereco;
    private LocalTime horarioFuncionamento;
    private LocalDate data;
    private String telefone;
    private String descricao;
    private boolean anexaDoc;

    @ManyToMany
    @JoinTable(
        name = "empresa_itens",
        joinColumns = @JoinColumn(name = "empresa_id"),
        inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<ItemResiduo> itensQueRecebe;

    public EmpresaColetora() {}

    public EmpresaColetora(String nome, String endereco, LocalTime horarioFuncionamento,
                           String telefone, String descricao, boolean anexaDoc, LocalDate data) {
        this.nome = nome;
        this.endereco = endereco;
        this.horarioFuncionamento = horarioFuncionamento;
        this.telefone = telefone;
        this.descricao = descricao;
        this.anexaDoc = anexaDoc;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public LocalTime getHorarioFuncionamento() {
        return horarioFuncionamento;
    }
    public void setHorarioFuncionamento(LocalTime horarioFuncionamento) {
        this.horarioFuncionamento = horarioFuncionamento;
    }

    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isAnexaDoc() {
        return anexaDoc;
    }
    public void setAnexaDoc(boolean anexaDoc) {
        this.anexaDoc = anexaDoc;
    }

    public LocalDate getData() {
        return data;
    }
    public void setData(LocalDate data) {
        this.data = data;
    }

    public List<ItemResiduo> getItensQueRecebe() {
        return itensQueRecebe;
    }
    public void setItensQueRecebe(List<ItemResiduo> itensQueRecebe) {
        this.itensQueRecebe = itensQueRecebe;
    }
}
