package grupo2.com.ecoPoint.Repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import grupo2.com.ecoPoint.Model.Entity.EmpresaGeradora;

@Repository
public interface EmpresaGeradoraRepository extends JpaRepository<EmpresaGeradora, Long> {

Optional<EmpresaGeradora> findByEmail(String email);

public EmpresaGeradora findEmpresaGeradoraById(Long id);
    
}