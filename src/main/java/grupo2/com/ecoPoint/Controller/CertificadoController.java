package grupo2.com.ecoPoint.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import grupo2.com.ecoPoint.Model.Entity.Certificado;
import grupo2.com.ecoPoint.Service.CertificadoService;

@RestController
@RequestMapping("/certificados")
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

 // CREATE
    @PostMapping
    public Certificado criarCertificado(@RequestBody Certificado certificado) {
        return certificadoService.criarCertificado(certificado);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Certificado atualizarCertificado(@PathVariable Long id, @RequestBody Certificado certificado) {
        return certificadoService.atualizarCertificado(id, certificado);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void deletarCertificado(@PathVariable Long id) {
        certificadoService.deletarCertificado(id);
    }
}


