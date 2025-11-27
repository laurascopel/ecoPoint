package grupo2.com.ecoPoint.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import grupo2.com.ecoPoint.Model.Entity.Solicitacao;

@Repository
public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Long> {

public Solicitacao findSolicitacaoById(Long id);

}
