package grupo2.com.ecoPoint.Model.Entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class EmpresaEnvia {
@Id
private Integer id;
private String nome;
private String cnpj;
private String endereco;
private String telefone;
  
public EmpresaEnvia() {
}

public EmpresaEnvia(Integer id, String nome, String cnpj, String endereco, String telefone) {
    this.id = id;
    this.nome = nome;
    this.cnpj = cnpj;
    this.endereco = endereco;
    this.telefone = telefone;
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

   public String getCnpj() {
      return cnpj;
  }
    public void setCnpj(String cnpj) {
      this.cnpj = cnpj;
  }

  public String getEndereco() {
      return endereco;
  }
    public void setEndereco(String endereco) {
      this.endereco = endereco;
  }

  public String getTelefone() {
      return telefone;
  }
    public void setTelefone(String telefone) {
      this.telefone = telefone;
  }

}
