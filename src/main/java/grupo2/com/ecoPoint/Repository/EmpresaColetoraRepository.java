package grupo2.com.ecoPoint.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import grupo2.com.ecoPoint.Model.Entity.EmpresaColetora;

@Repository
public interface EmpresaColetoraRepository extends CrudRepository<EmpresaColetora, Integer> {
    
    List<EmpresaColetora> findAll();
    List<EmpresaColetora> findByItensQueRecebe_Id(Long itemId);
    EmpresaColetora findEmpresaColetoraById(Long id);
}
