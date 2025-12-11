package grupo2.com.ecoPoint.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import grupo2.com.ecoPoint.Model.Entity.Certificado;

@Repository
public interface CertificadoRepository extends JpaRepository<Certificado, Long> {

public Certificado findCertificadoById(Long id);

@Query("SELECT c.documento FROM Certificado c WHERE c.solicitacao.id = :id")
byte[] buscarArquivo(@Param("id") Long id);

}
