package grupo2.com.ecoPoint.Service;

import grupo2.com.ecoPoint.Model.Entity.Certificado;
import grupo2.com.ecoPoint.Repository.CertificadoRepository;

public class CertificadoService {
    
    private final CertificadoRepository certificadoRepository;
    
    public CertificadoService(CertificadoRepository certificadoRepository, Long id) {
        this.certificadoRepository = certificadoRepository;
    }

    public Certificado salvarCertificado(Certificado certificado) {
        return certificadoRepository.save(certificado);
    }

}
