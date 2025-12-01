package grupo2.com.ecoPoint.Service;


import java.util.List;

import org.springframework.stereotype.Service;

import grupo2.com.ecoPoint.Model.Entity.EmpresaGeradora;
import grupo2.com.ecoPoint.Repository.EmpresaGeradoraRepository;

@Service
public class EmpresaGeradoraService {

    private final EmpresaGeradoraRepository empresaGeradoraRepository;

    
    public EmpresaGeradoraService(EmpresaGeradoraRepository empresaGeradoraRepository) {
        this.empresaGeradoraRepository = empresaGeradoraRepository;
    
    }

    public List<EmpresaGeradora> getAllEmpresaGeradora() {
        return empresaGeradoraRepository.findAll();
    }

    public EmpresaGeradora getEmpresaGeradoraById(Long id) {
        return empresaGeradoraRepository.findEmpresaGeradoraById(id);       
    }

    public EmpresaGeradora salvarEmpresaGeradora(EmpresaGeradora empresaGeradora) {
        return empresaGeradoraRepository.save(empresaGeradora);
    }

    public EmpresaGeradora atualizarEmpresaGeradora(Long id, EmpresaGeradora empresaGeradoraAtualizada) {
        EmpresaGeradora empresaGeradora = empresaGeradoraRepository.findEmpresaGeradoraById(id);

        empresaGeradora.setNome(empresaGeradoraAtualizada.getNome());
        empresaGeradora.setCnpj(empresaGeradoraAtualizada.getCnpj());
        empresaGeradora.setEndereco(empresaGeradoraAtualizada.getEndereco());
        empresaGeradora.setTelefone(empresaGeradoraAtualizada.getTelefone());

        return empresaGeradoraRepository.save(empresaGeradora);
        
    };

    public void deletarEmpresaGeradora(Long id) {
        empresaGeradoraRepository.deleteById(id);
    }
}
    