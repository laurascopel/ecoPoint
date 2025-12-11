package grupo2.com.ecoPoint.Repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import grupo2.com.ecoPoint.Model.Entity.Solicitacao;

@Repository
public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Long> {

public Solicitacao findSolicitacaoById(Long id);

public List<Solicitacao> findAllByEmpresaColetoraId(Long coletoraId);

public List<Solicitacao> findAllByEmpresaGeradoraId(Long geradoraId);

}
