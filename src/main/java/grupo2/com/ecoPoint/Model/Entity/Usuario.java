package grupo2.com.ecoPoint.Model.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Usuario {

    @Id
    private Long id;
    private String email;
    private String senha;
    private Enum tipoUsuario;

      public Usuario() {
  }

  public Usuario(Long id, String email, String senha, Enum tipoUsuario) {
    this.id = id;
    this.email = email;
    this.senha = senha;
    this.tipoUsuario = tipoUsuario;
  }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    } 

    public Enum getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(Enum tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    } 
}
