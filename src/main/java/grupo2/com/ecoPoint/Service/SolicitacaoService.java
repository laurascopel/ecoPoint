package grupo2.com.ecoPoint.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import grupo2.com.ecoPoint.Model.Entity.Certificado;
import grupo2.com.ecoPoint.Model.Entity.EmpresaColetora;
import grupo2.com.ecoPoint.Model.Entity.EmpresaGeradora;
import grupo2.com.ecoPoint.Model.Entity.ItemResiduo;
import grupo2.com.ecoPoint.Model.Entity.Solicitacao;
import grupo2.com.ecoPoint.Model.Entity.StatusSolicitacao;
import grupo2.com.ecoPoint.Repository.EmpresaColetoraRepository;
import grupo2.com.ecoPoint.Repository.EmpresaGeradoraRepository;
import grupo2.com.ecoPoint.Repository.ItemResiduoRepository;
import grupo2.com.ecoPoint.Repository.SolicitacaoRepository;

@Service
public class SolicitacaoService {

    private final SolicitacaoRepository solicitacaoRepository;
    private final EmpresaGeradoraRepository geradoraRepository;
    private final EmpresaColetoraRepository coletoraRepository;
    private final ItemResiduoRepository itemRepository;


    public SolicitacaoService(SolicitacaoRepository solicitacaoRepository, EmpresaGeradoraRepository geradoraRepository, EmpresaColetoraRepository coletoraRepository,
        ItemResiduoRepository itemRepository) 
   
    {
        this.solicitacaoRepository = solicitacaoRepository;
        this.geradoraRepository = geradoraRepository;
        this.coletoraRepository = coletoraRepository;
        this.itemRepository = itemRepository;
       }

    
    public List<EmpresaColetora> buscarColetorasPorItens(List<Long> itensIds) {
        List<ItemResiduo> itensSelecionados = itemRepository.findAllById(itensIds);
        List<EmpresaColetora> empresasColetoras = coletoraRepository.findAll();

        return empresasColetoras.stream()
                .filter(c -> c.getItensQueRecebe().containsAll(itensSelecionados))
                .toList();
    }
    
    public Solicitacao criarSolicitacao(Long geradoraId, Long coletoraId, LocalDate dataAgendada, List<Long> itensIds) {

    EmpresaGeradora empresaGeradora = geradoraRepository.findEmpresaGeradoraById(geradoraId);
    EmpresaColetora empresaColetora = coletoraRepository.findEmpresaColetoraById(coletoraId);
    List<ItemResiduo> itensSelecionados = itemRepository.findAllById(itensIds);

    Solicitacao solicitacao = new Solicitacao();
    solicitacao.setEmpresaGeradora(empresaGeradora);
    solicitacao.setEmpresaColetora(empresaColetora);
    solicitacao.setDataSolicitacao(LocalDate.now());
    solicitacao.setDataAgendada(dataAgendada);
    solicitacao.setItens(itensSelecionados);
    solicitacao.setStatus(StatusSolicitacao.PENDENTE);

    return solicitacaoRepository.save(solicitacao);
 }

    public Solicitacao aceitarSolicitacao(Long id) {
        Solicitacao solicitacao = solicitacaoRepository.findSolicitacaoById(id);

        if (solicitacao.getStatus() == StatusSolicitacao.PENDENTE) {
            solicitacao.setStatus(StatusSolicitacao.ACEITA);
        }
        return solicitacaoRepository.save(solicitacao);
    }

    public Solicitacao recusarSolicitacao(Long id) {
        Solicitacao solicitacao = solicitacaoRepository.findSolicitacaoById(id);

        if (solicitacao.getStatus() == StatusSolicitacao.PENDENTE) {
            solicitacao.setStatus(StatusSolicitacao.RECUSADA);
        }
        return solicitacaoRepository.save(solicitacao);
    }

    public Solicitacao coletarSolicitacao(Long id) {
        Solicitacao solicitacao = solicitacaoRepository.findSolicitacaoById(id);

        if (solicitacao.getStatus() == StatusSolicitacao.ACEITA) {
            solicitacao.setStatus(StatusSolicitacao.COLETADA);
        }
        return solicitacaoRepository.save(solicitacao);
    }

    public Solicitacao adicionarCertificado(Long solicitacaoId, byte[] arquivo, String tipo) {

        Solicitacao solicitacao = solicitacaoRepository.findSolicitacaoById(solicitacaoId);
        Certificado certificado = new Certificado();

        certificado.setDocumento(arquivo);
        certificado.setTipo(tipo);
        certificado.setDataEmissao(LocalDate.now());
        certificado.setSolicitacao(solicitacao);

        solicitacao.setCertificado(certificado);

        solicitacao.setStatus(StatusSolicitacao.FINALIZADA);

        return solicitacaoRepository.save(solicitacao);
    }

    public List<Solicitacao> getAllSolicitacao() {
        return solicitacaoRepository.findAll();
    }

    public Solicitacao getSolicitacaoById(Long id) {
        return solicitacaoRepository.findSolicitacaoById(id);       
    }

    public Solicitacao atualizarSolicitacao(Long id, Solicitacao solicitacaoAtualizada) {
        Solicitacao solicitacao = solicitacaoRepository.findSolicitacaoById(id);

        solicitacao.setEmpresaGeradora(solicitacaoAtualizada.getEmpresaGeradora());
        solicitacao.setEmpresaColetora(solicitacaoAtualizada.getEmpresaColetora());
        solicitacao.setDataAgendada(solicitacaoAtualizada.getDataAgendada());
        solicitacao.setItens(solicitacaoAtualizada.getItens());
        solicitacao.setStatus(solicitacaoAtualizada.getStatus());

        return solicitacaoRepository.save(solicitacao);
        
    }

}
