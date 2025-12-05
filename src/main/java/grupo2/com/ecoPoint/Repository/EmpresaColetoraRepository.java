package grupo2.com.ecoPoint.Repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import grupo2.com.ecoPoint.Model.Entity.EmpresaColetora;

@Repository
public interface EmpresaColetoraRepository extends JpaRepository<EmpresaColetora, Long> {

Optional<EmpresaColetora> findByEmail(String email);

public EmpresaColetora findEmpresaColetoraById(Long id);

}
