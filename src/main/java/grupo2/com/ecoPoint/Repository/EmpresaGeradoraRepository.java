package grupo2.com.ecoPoint.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import grupo2.com.ecoPoint.Model.Entity.EmpresaGeradora;

@Repository
public interface EmpresaGeradoraRepository extends CrudRepository<EmpresaGeradora, Integer> {
    
    List<EmpresaGeradora> findAll();
    
    EmpresaGeradora findEmpresaGeradoraById(Long id);
}