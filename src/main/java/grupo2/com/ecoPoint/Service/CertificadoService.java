package grupo2.com.ecoPoint.Service;
import java.util.List;

import org.springframework.stereotype.Service;

import grupo2.com.ecoPoint.Model.Entity.Certificado;
import grupo2.com.ecoPoint.Repository.CertificadoRepository;

@Service
public class CertificadoService {
    
    private final CertificadoRepository certificadoRepository;
    
    public CertificadoService(CertificadoRepository certificadoRepository) {
        this.certificadoRepository = certificadoRepository;
    }

    public Certificado criarCertificado(Certificado certificado) {
        return certificadoRepository.save(certificado);
    }

    public Certificado getCertificadoById(Long id) {
        return certificadoRepository.findCertificadoById(id);       
    }

    public void deletarCertificado(Long id) {
        certificadoRepository.deleteById(id);
    }

    public Certificado atualizarCertificado(Long id, Certificado certificadoAtualizado) {
        Certificado certificado = certificadoRepository.findCertificadoById(id);

        //certificado.setNome(certificadoAtualizado.getNome());
        certificado.setDataEmissao(certificadoAtualizado.getDataEmissao());
        //certificado.setEmpresaColetora(certificadoAtualizado.getEmpresaColetora());
        //certificado.setEmpresaGeradora(certificadoAtualizado.getEmpresaGeradora());
        //certificado.setDescricao(certificadoAtualizado.getDescricao());

        return certificadoRepository.save(certificado);
        
    };

    public List<Certificado> getAllCertificado() {
        return certificadoRepository.findAll();
    }

    public byte[] baixarCertificado(Long id) {
    return certificadoRepository.buscarArquivo(id);
}
}
