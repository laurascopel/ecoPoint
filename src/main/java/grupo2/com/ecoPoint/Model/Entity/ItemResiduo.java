package grupo2.com.ecoPoint.Model.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ItemResiduo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome; // nome do resíduo, ex: "Papel", "Plástico", "Metal"

 
    public ItemResiduo() {  /* Construtor vazio obrigatório para JPA */

    } 

    public ItemResiduo(String nome) { /* Construtor */
        this.nome = nome;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
}
