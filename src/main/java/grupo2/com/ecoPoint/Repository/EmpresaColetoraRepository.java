package grupo2.com.ecoPoint.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import grupo2.com.ecoPoint.Model.Entity.EmpresaColetora;

@Repository
public interface EmpresaColetoraRepository extends JpaRepository<EmpresaColetora, Long> {

public EmpresaColetora findEmpresaColetoraById(Long id);

}
