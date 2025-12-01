package grupo2.com.ecoPoint.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import grupo2.com.ecoPoint.Model.Entity.Solicitacao;
import grupo2.com.ecoPoint.Repository.SolicitacaoRepository;


@Service
public class SolicitacaoService {

    private final SolicitacaoRepository solicitacaoRepository;
    
    public SolicitacaoService(SolicitacaoRepository solicitacaoRepository) {
        this.solicitacaoRepository = solicitacaoRepository;
    }

}
