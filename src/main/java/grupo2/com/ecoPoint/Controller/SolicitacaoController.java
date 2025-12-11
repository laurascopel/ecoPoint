package grupo2.com.ecoPoint.Controller;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;
import grupo2.com.ecoPoint.Model.Entity.EmpresaColetora;
import grupo2.com.ecoPoint.Model.Entity.Solicitacao;
import grupo2.com.ecoPoint.Service.SolicitacaoService;
import grupo2.com.ecoPoint.dto.SolicitacaoDTO;
import org.springframework.http.MediaType;



@RestController
@RequestMapping("/solicitacao")
@CrossOrigin(origins = "*")

public class SolicitacaoController {
    public final SolicitacaoService solicitacaoService;

    public SolicitacaoController(SolicitacaoService solicitacaoService) {
        this.solicitacaoService = solicitacaoService;
    }
    
    @GetMapping
    public List<Solicitacao> getAllSolicitacao() {
        return solicitacaoService.getAllSolicitacao();
    }

    @GetMapping("/{id}")
    public Solicitacao getSolicitacaoById(@PathVariable Long id) {
        return solicitacaoService.getSolicitacaoById(id);
    }

    @GetMapping("/minha/{coletoraId}")
    public List<Solicitacao> getSolicitacoesColetora(@PathVariable Long coletoraId) {
        return solicitacaoService.getSolicitacoesColetora(coletoraId);   
    }

    @GetMapping("/minhas/{geradoraId}")
    public List<Solicitacao> getSolicitacoesGeradora(@PathVariable Long geradoraId) {
        return solicitacaoService.getSolicitacoesGeradora(geradoraId);   
    }

    @PostMapping
    public Solicitacao criarSolicitacao(@RequestBody SolicitacaoDTO dto) {
        return solicitacaoService.criarSolicitacao(
            dto.getGeradoraId(),
            dto.getColetoraId(),
            LocalDate.parse(dto.getDataAgendada()),
            dto.getItensIds()
        );
}

    @PutMapping("/{id}")  
    public Solicitacao atualizarSolicitacao(@PathVariable Long id, @RequestBody Solicitacao solicitacao) {
        return solicitacaoService.atualizarSolicitacao(id, solicitacao);

 }

  @PostMapping(value = "/{id}/certificado", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Solicitacao> enviarCertificado(
        @PathVariable Long id,
        @RequestParam("arquivo") MultipartFile arquivo,
        @RequestParam("tipo") String tipo
    ) throws IOException {

     Solicitacao atualizado = solicitacaoService.adicionarCertificado(
        id,
        arquivo.getBytes(),
        tipo
     );

    return ResponseEntity.ok(atualizado);
}

    @PutMapping("/{id}/aceitar")
    public Solicitacao aceitar(@PathVariable Long id) {
        return solicitacaoService.aceitarSolicitacao(id);
    }

    @PutMapping("/{id}/recusar")
    public Solicitacao recusar(@PathVariable Long id) {
        return solicitacaoService.recusarSolicitacao(id);
    }

    @PutMapping("/{id}/coletar")
    public Solicitacao coletar(@PathVariable Long id) {
        return solicitacaoService.coletarSolicitacao(id);
    }

    @PostMapping("/coletoras-por-itens")
    public List<EmpresaColetora> buscarColetorasPorItens(@RequestBody List<Long> itens) {
    return solicitacaoService.buscarColetorasPorItens(itens);
}

}