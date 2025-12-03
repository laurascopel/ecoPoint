package grupo2.com.ecoPoint.Controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import grupo2.com.ecoPoint.Model.Entity.Solicitacao;
import grupo2.com.ecoPoint.Service.SolicitacaoService;

@RestController
@RequestMapping("/solicitacao")

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

    @PostMapping
    public Solicitacao criarSolicitacao(
    @RequestParam Long geradoraId,
    @RequestParam Long coletoraId,
    @RequestParam String dataAgendada,
    @RequestParam List<Long> itensIds
){
    return solicitacaoService.criarSolicitacao(
        geradoraId,
        coletoraId,
        LocalDate.parse(dataAgendada),
        itensIds
    );
}

     @PutMapping("/{id}")  
    public Solicitacao atualizarSolicitacao(@PathVariable Long id, @RequestBody Solicitacao solicitacao) {
        return solicitacaoService.atualizarSolicitacao(id, solicitacao);

 }

    @PostMapping("/{id}/certificado")
    public Solicitacao enviarCertificado(
        @PathVariable Long id,
        @RequestParam("arquivo") MultipartFile arquivo,
        @RequestParam("tipo") String tipo
    ) throws IOException {

        return solicitacaoService.adicionarCertificado(
            id,
            arquivo.getBytes(),
            tipo
        );
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

}
