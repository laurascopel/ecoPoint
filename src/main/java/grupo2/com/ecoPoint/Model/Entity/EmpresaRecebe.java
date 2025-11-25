package grupo2.com.ecoPoint.Model.Entity;

import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class EmpresaRecebe {
    @Id
    private Integer id;
    private String nome;
    private String endereco;
    private LocalTime horarioFuncionamento;
    private String telefone;
    private String descricao;
    private boolean anexaDoc;
  
    public EmpresaRecebe() {
    }

    public EmpresaRecebe(Integer id, String nome, String endereco, LocalTime horarioFuncionamento, String telefone, String descricao, Boolean anexaDoc) {
    this.id = id;
    this.nome = nome;
    this.endereco = endereco;
    this.horarioFuncionamento = horarioFuncionamento;
    this.telefone = telefone;
    this.descricao = descricao;
    this.anexaDoc = anexaDoc;
    }

    public Integer getId() {
      return id;
  }
    public void setId(Integer id) {
      this.id = id;
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
    public Boolean getAnexaDoc() {
      return anexaDoc;
  }
    public void setAnexaDoc(Boolean anexaDoc) {
      this.anexaDoc = anexaDoc;
  }
}
