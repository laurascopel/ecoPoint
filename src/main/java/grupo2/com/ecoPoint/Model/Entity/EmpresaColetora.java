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
    private String email;
    private String senha;
    private String nome;
    private String endereco;
    private String cnpj;
    private LocalTime horarioFuncionamento;
    private LocalDate data;
    private String telefone;
    private String descricao;

    @ManyToMany
    @JoinTable(                              /* Varios itensResiduos para varias empresasas) */
        name = "empresa_coletora_itens",    /* Sem a criação desta tabela intermediaria para relacionar empresa com item, nao podemos ter o ManyToMany*/
        joinColumns = @JoinColumn(name = "empresa_coletora_id"),
        inverseJoinColumns = @JoinColumn(name = "item_residuo_id")
        /* Relacionando o item-residuo com a empresa */

    )
    private List<ItemResiduo> itensQueRecebe; 

    public EmpresaColetora() {          /* Construtor vazio obrigatório para JPA */

    }  

    public EmpresaColetora(String nome, String endereco, LocalTime horarioFuncionamento,
                           String telefone, String descricao, LocalDate data, String cnpj, String email, String senha){
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.endereco = endereco;
        this.horarioFuncionamento = horarioFuncionamento;
        this.telefone = telefone;
        this.descricao = descricao;
        this.data = data;
        this.cnpj = cnpj;
    }

    public Long getId() {
        return id;
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

     public String getCnpj() {
        return cnpj;
    }
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
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
