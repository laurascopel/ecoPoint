package grupo2.com.ecoPoint.Controller;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import grupo2.com.ecoPoint.Model.Entity.Certificado;
import grupo2.com.ecoPoint.Service.CertificadoService;

@RestController
@RequestMapping("/certificados")
@CrossOrigin(origins = "*")
public class CertificadoController {

    private final CertificadoService certificadoService;

    public CertificadoController(CertificadoService certificadoService) {
        this.certificadoService = certificadoService;
    }

    // GET ALL
    @GetMapping
    public List<Certificado> getAllCertificado() {
        return certificadoService.getAllCertificado();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public Certificado getCertificadoById(@PathVariable Long id) {
        return certificadoService.getCertificadoById(id);
    }

    @GetMapping("/{solicitacaoId}/download")
    public ResponseEntity<byte[]> downloadCertificado(@PathVariable Long solicitacaoId) {

        byte[] arquivo = certificadoService.baixarCertificado(solicitacaoId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "certificado.pdf");

        return new ResponseEntity<>(arquivo, headers, HttpStatus.OK);
    }
    

}


